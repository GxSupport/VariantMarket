package uz.gxteam.variantmarket.viewModels.registerVm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpHeaders
import com.google.gson.JsonElement
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uz.gxteam.variantmarket.BuildConfig.BASE_URL
import uz.gxteam.variantmarket.models.confirm.resConfirm.ResponseConfirmData
import uz.gxteam.variantmarket.models.confirm.sendConfirm.ConfirmData
import uz.gxteam.variantmarket.models.register.reqRegister.ReqRegister
import uz.gxteam.variantmarket.models.register.resRegister.ResponseRegisterData
import uz.gxteam.variantmarket.repository.apiRepository.ApiRepositoryImpl
import uz.gxteam.variantmarket.usesCase.apiUsesCase.ApiUsesCase
import uz.gxteam.variantmarket.utils.appConstant.AppConstant.API
import uz.gxteam.variantmarket.utils.appConstant.AppConstant.EMPTY_MAP
import uz.gxteam.variantmarket.utils.appConstant.AppConstant.JSON_DATA
import uz.gxteam.variantmarket.utils.appConstant.AppConstant.NO_INTERNET_CONNECTION
import uz.gxteam.variantmarket.utils.appConstant.AppConstant.URL_CONFIRM
import uz.gxteam.variantmarket.utils.appConstant.AppConstant.URL_REGISTER
import uz.gxteam.variantmarket.utils.networkHelper.NetworkHelper
import uz.gxteam.variantmarket.utils.responseState.ResponseState
import uz.gxteam.variantmarket.utils.sharedPreferences.MySharedPreferences
import javax.inject.Inject

@HiltViewModel
class RegisterVM  @Inject constructor(
    private val apiUsesCase: ApiUsesCase,
    private val mySharedPreferences: MySharedPreferences,
    private val networkHelper: NetworkHelper
):ViewModel() {
    // TODO: getSharedPreferences
    val myshared get() = mySharedPreferences

    // TODO: RegisterUrl
    private val urlRegister = "$BASE_URL/$API/$URL_REGISTER"

    // TODO: RegisterData
    val responseRegisterData:StateFlow<ResponseState<JsonElement?>> get() = _responseRegisterData
    private val _responseRegisterData = MutableStateFlow<ResponseState<JsonElement?>>(ResponseState.Loading)

    // TODO: Methode Register market
    fun registrationMarketClient(requestRegister:ReqRegister) = viewModelScope.launch {
       if (networkHelper.isNetworkConnected()){
           _responseRegisterData.emit(ResponseState.Loading)
           try {
               apiUsesCase.methodePost(urlRegister,requestRegister,getHeaderMap())
                   .collect{ result-> _responseRegisterData.emit(result) }
           }catch (e:Exception){
               _responseRegisterData.emit(ResponseState.Error(e.hashCode(),e.message))
           }
       }else{
           _responseRegisterData.emit(ResponseState.Error(NO_INTERNET_CONNECTION))
       }
    }



    // TODO: Url Confirm data
    private val urlConfirm = "$BASE_URL/$API/$URL_CONFIRM"


    // TODO: RegisterData
    val confirmData:StateFlow<ResponseState<JsonElement?>> get() = _confirmData
    private val _confirmData = MutableStateFlow<ResponseState<JsonElement?>>(ResponseState.Loading)


    // TODO: Methode Confirm SMS CODE
    fun confirmClient(confirmData: ConfirmData) = viewModelScope.launch {
        if (networkHelper.isNetworkConnected()){
            _responseRegisterData.emit(ResponseState.Loading)
            try {
                apiUsesCase.methodePost(urlConfirm,confirmData,getHeaderMap())
                    .collect{ result-> _confirmData.emit(result) }

            }catch (e:Exception){
                _confirmData.emit(ResponseState.Error(e.hashCode(),e.message))
            }
        }else{
            _confirmData.emit(ResponseState.Error(NO_INTERNET_CONNECTION))
        }
    }


    // TODO: Register or login Header
    fun getHeaderMap():HashMap<String,String>{
        var headerMap = HashMap<String,String>()
        headerMap[HttpHeaders.ACCEPT] = JSON_DATA
        headerMap[HttpHeaders.CONTENT_TYPE] = JSON_DATA
        return headerMap
    }

}