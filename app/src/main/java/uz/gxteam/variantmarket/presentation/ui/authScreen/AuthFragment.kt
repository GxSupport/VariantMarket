package uz.gxteam.variantmarket.presentation.ui.authScreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import uz.gxteam.variantmarket.R
import uz.gxteam.variantmarket.databinding.FragmentAuthBinding
import uz.gxteam.variantmarket.models.auth.AuthData
import uz.gxteam.variantmarket.models.register.resRegister.ResponseRegisterData
import uz.gxteam.variantmarket.presentation.ui.base.BaseFragment
import uz.gxteam.variantmarket.utils.extensions.enabled
import uz.gxteam.variantmarket.utils.extensions.enabledFalse
import uz.gxteam.variantmarket.utils.extensions.fetchResult
import uz.gxteam.variantmarket.viewModels.authVm.AuthVm
import uz.gxteam.variantmarket.viewModels.registerVm.RegisterVM

@AndroidEntryPoint
class AuthFragment : BaseFragment<FragmentAuthBinding>() {
    private var isPhone:Boolean?=false
    private var isPassword:Boolean?=false
    private val authVm:AuthVm by viewModels()
    override fun setup(savedInstanceState: Bundle?) {
        binding.apply {
            appCompositionRootAuth.navigationBarColor(R.color.white)

            phoneNumber.addTextChangedListener {
                if (phoneNumber.rawText.length==12){
                    password.requestFocus()
                    isPhone = true
                }else{
                    isPhone = false
                }
                buttonEnabled(isPhone,isPassword)
            }

            password.addTextChangedListener {
                isPassword = it.toString().trim().length>=6
                buttonEnabled(isPhone,isPassword)
            }


            register.setOnClickListener {
                appCompositionRootAuth.screenNavigate.createRegistrationPage()
            }

            passwordForgot.setOnClickListener {
                appCompositionRootAuth.screenNavigate.createNewPasswordPage()
            }
            login.setOnClickListener {
                val password = password.text.toString().trim()
                val phone = phoneNumber.rawText.toString().trim()
                val authData = AuthData(password,phone)
                authVm.authApp(authData)
                launch {
                    authVm.authData.fetchResult(appCompositionRootAuth.uiControllerApp,{ any ->
                        Log.e("DataAuth", any.toString())
                    },{ errorCode, errorMessage ->
                        appCompositionRootAuth.uiControllerApp.error(errorCode,errorMessage){isClick ->  }
                    })
                }
            }
        }
    }

    fun buttonEnabled(isPhone:Boolean?,isPassword:Boolean?){
        if (isPhone == true && isPassword == true){
            appCompositionRootAuth.drawableColorUpdate(binding.login,R.color.app_background)
            binding.login.enabled()
        }else{
            appCompositionRootAuth.drawableColorUpdate(binding.login,R.color.app_background_hint)
            binding.login.enabledFalse()
        }
    }



    override fun start(savedInstanceState: Bundle?) {

    }

    override fun inflateViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentAuthBinding =
        FragmentAuthBinding.inflate(inflater,container,false)
}