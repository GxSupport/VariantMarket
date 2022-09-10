package uz.gxteam.variantmarket.presentation.ui.register

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import uz.gxteam.variantmarket.R
import uz.gxteam.variantmarket.databinding.FragmentConfirmUserBinding
import uz.gxteam.variantmarket.databinding.FragmentRegistrationBinding
import uz.gxteam.variantmarket.models.register.reqRegister.ReqRegister
import uz.gxteam.variantmarket.models.register.resRegister.ResponseRegisterData
import uz.gxteam.variantmarket.presentation.ui.base.BaseFragment
import uz.gxteam.variantmarket.utils.extensions.*
import uz.gxteam.variantmarket.viewModels.registerVm.RegisterVM

@AndroidEntryPoint
class RegistrationFragment : BaseFragment<FragmentRegistrationBinding>() {
    private var isPasswordLength:Boolean?=false
    private var isPasswordConfirmLength:Boolean?=false
    private var isPhoneNumberLength:Boolean?=false
    private var isNameLength:Boolean?=false

    // TODO: Register ViewModel
    private val registerVM:RegisterVM by viewModels()
    private val myShared get() = registerVM.myshared
    override fun setup(savedInstanceState: Bundle?) {
        binding.apply {
            fullName.addTextChangedListener {
                isNameLength = it?.trim().toString().isNotNullOrEmpty()
                buttonEnabledCreate(isPasswordLength,isPasswordConfirmLength,isPhoneNumberLength,isNameLength)
            }
            phoneNumber.addTextChangedListener {
                if (phoneNumber.rawText.length==12){
                    password.requestFocus()
                    isPhoneNumberLength = true
                }else{
                    isPhoneNumberLength = false
                }
                buttonEnabledCreate(isPasswordLength,isPasswordConfirmLength,isPhoneNumberLength,isNameLength)
            }

            password.addTextChangedListener {
                isPasswordLength = password.length()>=6
                buttonEnabledCreate(isPasswordLength,isPasswordConfirmLength,isPhoneNumberLength,isNameLength)
            }
            passwordConfirmation.addTextChangedListener {
                isPasswordConfirmLength = password.length()>=6
                buttonEnabledCreate(isPasswordLength,isPasswordConfirmLength,isPhoneNumberLength,isNameLength)
            }
            register.setOnClickListener {
                val fullNameData = fullName.text.toString().trim()
                val phoneNumberData = phoneNumber.rawText.toString().trim()
                val passwordData = password.text.toString().trim()
                val passwordConfirmationData = passwordConfirmation.text.toString().trim()
                if (!fullNameData.isNotNullOrEmpty()){
                    fullName.error = requireActivity().getString(R.string.not_full_name)
                } else if (!phoneNumberData.isNotNullOrEmpty()){
                    phoneNumber.error = requireActivity().getString(R.string.not_phone_number)
                } else if (!passwordData.isNotNullOrEmpty()){
                    password.error = requireActivity().getString(R.string.not_password)
                } else if (!passwordConfirmationData.isNotNullOrEmpty()){
                    passwordConfirmation.error = requireActivity().getString(R.string.not_password)
                }else if (passwordData.length<6){
                    password.error = requireActivity().getString(R.string.password_length)
                } else if (passwordConfirmationData.length<6){
                    passwordConfirmation.error = requireActivity().getString(R.string.password_length)
                } else{
                    val reqRegister = ReqRegister(fullNameData,passwordData,passwordConfirmationData,phoneNumberData)
                    registerVM.registrationMarketClient(reqRegister)
                    launch {
                        registerVM.responseRegisterData.fetchResult(appCompositionRootAuth.uiControllerApp,{ responseRegisterData ->
                            val registerData = responseRegisterData?.gsonData()
                            myShared.registerData = registerData
                            appCompositionRootAuth.screenNavigate.createConfirmPage(responseRegisterData!!,reqRegister)
                        },{ errorCode, errorMessage ->
                            appCompositionRootAuth.uiControllerApp.error(errorCode,errorMessage){
                                if(it){ }
                            }
                        })
                    }
                }
            }
        }
    }

    override fun start(savedInstanceState: Bundle?) {

    }


    fun buttonEnabledCreate(
        isPasswordLength:Boolean?,
        isPasswordConfirmLength:Boolean?,
        isPhoneNumberLength:Boolean?,
        isNameLength:Boolean?
    ){
        binding.apply {
            if (isPasswordConfirmLength == true && isPasswordLength == true && isPhoneNumberLength == true && isNameLength == true)
            {
                appCompositionRootAuth.drawableColorUpdate(binding.register,R.color.app_background)
                binding.register.enabled()
            }else{
                appCompositionRootAuth.drawableColorUpdate(binding.register,R.color.auth_btn_color)
                binding.register.enabledFalse()
            }
        }
    }

    override fun inflateViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentRegistrationBinding =
        FragmentRegistrationBinding.inflate(inflater,container,false)
}