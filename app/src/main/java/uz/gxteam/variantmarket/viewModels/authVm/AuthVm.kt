package uz.gxteam.variantmarket.viewModels.authVm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.net.HttpHeaders
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uz.gxteam.variantmarket.BuildConfig.BASE_URL
import uz.gxteam.variantmarket.models.auth.AuthData
import uz.gxteam.variantmarket.repository.apiRepository.ApiRepositoryImpl
import uz.gxteam.variantmarket.usesCase.apiUsesCase.ApiUsesCase
import uz.gxteam.variantmarket.utils.appConstant.AppConstant.API
import uz.gxteam.variantmarket.utils.appConstant.AppConstant.AUTH_LOGIN
import uz.gxteam.variantmarket.utils.appConstant.AppConstant.JSON_DATA
import uz.gxteam.variantmarket.utils.appConstant.AppConstant.NO_INTERNET_CONNECTION
import uz.gxteam.variantmarket.utils.networkHelper.NetworkHelper
import uz.gxteam.variantmarket.utils.responseState.ResponseState
import uz.gxteam.variantmarket.utils.sharedPreferences.MySharedPreferences
import javax.inject.Inject
@HiltViewModel
class AuthVm @Inject constructor(
    private val networkHelper: NetworkHelper,
    private val mySharedPreferences: MySharedPreferences,
    private val apiUsesCase: ApiUsesCase
):ViewModel() {
    // TODO: Auth Application
    val authData:StateFlow<ResponseState<Any?>> get() = _authData
    private val _authData = MutableStateFlow<ResponseState<Any?>>(ResponseState.Loading)

    // TODO:  Application auth URL
    private val urlAuth = "$BASE_URL/$API/$AUTH_LOGIN"

    // TODO: Methode Auth Application
    fun authApp(authData: AuthData) = viewModelScope.launch {
        if (networkHelper.isNetworkConnected()){
            _authData.emit(ResponseState.Loading)
            apiUsesCase.methodePost(urlAuth,authData,getMapHeader())
                .collect{response-> _authData.emit(response) }
        }else{
            _authData.emit(ResponseState.Error(NO_INTERNET_CONNECTION))
        }
    }

    fun getMapHeader():HashMap<String,String>{
        val mapHeader = HashMap<String,String>()
        mapHeader[HttpHeaders.AUTHORIZATION] = "${mySharedPreferences.tokenType} ${mySharedPreferences.accessToken}"
        mapHeader[HttpHeaders.ACCEPT] = JSON_DATA
        mapHeader[HttpHeaders.CONTENT_TYPE] = JSON_DATA
        return mapHeader
    }
}