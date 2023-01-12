package uz.gxteam.variantmarket.presentation.ui.register.confirmUser

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import uz.gxteam.variantmarket.R
import uz.gxteam.variantmarket.databinding.FragmentConfirmUserBinding
import uz.gxteam.variantmarket.models.confirm.resConfirm.ResponseConfirmData
import uz.gxteam.variantmarket.models.confirm.sendConfirm.ConfirmData
import uz.gxteam.variantmarket.models.register.reqRegister.ReqRegister
import uz.gxteam.variantmarket.models.register.resRegister.ResponseRegisterData
import uz.gxteam.variantmarket.presentation.activitys.MainActivity
import uz.gxteam.variantmarket.presentation.ui.base.BaseFragment
import uz.gxteam.variantmarket.utils.appConstant.AppConstant
import uz.gxteam.variantmarket.utils.appConstant.AppConstant.RESPONSE_REGISTER_DATA
import uz.gxteam.variantmarket.utils.extensions.*
import uz.gxteam.variantmarket.viewModels.registerVm.RegisterVM

@AndroidEntryPoint
class ConfirmUserFragment : BaseFragment<FragmentConfirmUserBinding>() {
    private val registerVM:RegisterVM by viewModels()
    private val myShared get() = registerVM.myshared
    private lateinit var countDownTimer: CountDownTimer
    private var startTime = 300000
    private var timeMilles = startTime
    private lateinit var responseRegisterData: ResponseRegisterData
    private lateinit var reqRegister: ReqRegister
    override fun setup(savedInstanceState: Bundle?) {
        binding.apply {
            startTimer()
            appCompositionRootAuth.navigationBarColor(R.color.app_background)
            linearRetryTime.setOnClickListener {
                registerVM.registrationMarketClient(reqRegister)
                launch {
                    registerVM.responseRegisterData.fetchResult(appCompositionRootAuth.uiControllerApp,{responseRegisterDataRes ->
                        if (responseRegisterDataRes != null) {
                            responseRegisterData = responseRegisterDataRes.parseClass(ResponseRegisterData::class.java)
                            startTimer()
                        }
                    },{ errorCode, errorMessage ->
                        appCompositionRootAuth.uiControllerApp.error(errorCode,errorMessage){ isClick -> }
                    })
                }
            }


            applyBtn.setOnClickListener {
                if (otpView.otp.isNotNullOrEmpty()){
                    myShared.codeConfirm = otpView.otp
                    val confirmData = ConfirmData(otpView.otp.toInt(),responseRegisterData.success.phone.toString())
                    registerVM.confirmClient(confirmData)
                    launch {
                        registerVM.confirmData.fetchResult(appCompositionRootAuth.uiControllerApp,{responseConfirmData ->
                            val responseConfirm = responseConfirmData?.parseClass(ResponseConfirmData::class.java)
                            myShared.accessToken = responseConfirm?.access_token
                            myShared.refreshToken = responseConfirm?.refresh_token
                            myShared.tokenType = responseConfirm?.token_type
                            appCompositionRootAuth.activityApp.startNewActivity(MainActivity::class.java)
                            appCompositionRootAuth.activityApp.finish()
                        },{ errorCode, errorMessage ->
                            appCompositionRootAuth.uiControllerApp.error(errorCode,errorMessage){ isClick ->
                                if (isClick){

                                }
                            }
                        })
                    }
                }
            }
        }
    }


    // 5min 1 min 60s   300
    private fun startTimer() {
        startTime = (responseRegisterData.success.expire_time * 60) * 1000
       countDownTimer = object:CountDownTimer(startTime.toLong(),1000) {
            override fun onTick(p0: Long) {
                timeMilles = p0.toInt()
                updateCountDownText()
            }

            override fun onFinish() {

            }
        }
        countDownTimer.start()
    }

    override fun start(savedInstanceState: Bundle?) {
        arguments.let {
            responseRegisterData = it?.getSerializable(RESPONSE_REGISTER_DATA) as ResponseRegisterData
            reqRegister = it.getSerializable(AppConstant.REQUEST_REGISTER) as ReqRegister
        }
    }

    fun updateCountDownText(){
        var minutes = (timeMilles/1000)/60
        var seconds = (timeMilles/1000)%60
        val time = String.format("%02d:%02d", minutes, seconds)
        binding.time.textApp(time)
    }

    override fun onDestroy() {
        countDownTimer.cancel()
        super.onDestroy()
    }

    override fun inflateViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentConfirmUserBinding =
        FragmentConfirmUserBinding.inflate(inflater,container,false)
}