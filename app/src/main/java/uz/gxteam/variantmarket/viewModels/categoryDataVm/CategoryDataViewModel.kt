package uz.gxteam.variantmarket.viewModels.categoryDataVm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonElement
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import uz.gxteam.variantmarket.usesCase.apiUsesCase.ApiUsesCase
import uz.gxteam.variantmarket.utils.appConstant.AppConstant.API
import uz.gxteam.variantmarket.utils.appConstant.AppConstant.CATEGORY_URL
import uz.gxteam.variantmarket.utils.appConstant.AppConstant.EMPTY_MAP
import uz.gxteam.variantmarket.utils.appConstant.AppConstant.NO_INTERNET_CONNECTION
import uz.gxteam.variantmarket.utils.networkHelper.NetworkHelper
import uz.gxteam.variantmarket.utils.responseState.ResponseState
import javax.inject.Inject
@HiltViewModel
class CategoryDataViewModel @Inject constructor(
    private val networkHelper: NetworkHelper,
    private val apiUsesCase: ApiUsesCase
):ViewModel() {
    val categoryData:StateFlow<ResponseState<JsonElement?>> get() = _categoryData
    private val _categoryData = MutableStateFlow<ResponseState<JsonElement?>>(ResponseState.Loading)
    fun getCategory(categoryId:Int?) = viewModelScope.launch {
        if (networkHelper.isNetworkConnected()) {
            _categoryData.emit(ResponseState.Loading)
            val queryMap = HashMap<String,String>()
            queryMap["id"] = categoryId.toString()
            apiUsesCase.methodeGet("/$API/$CATEGORY_URL",if (categoryId!=null) queryMap else EMPTY_MAP).collect { response->
                _categoryData.emit(response)
            }
        } else{
         _categoryData.emit(ResponseState.Error(NO_INTERNET_CONNECTION))
        }
    }

    val categoryDataChild:StateFlow<ResponseState<JsonElement?>> get() = _categoryDataChild
    private val _categoryDataChild = MutableStateFlow<ResponseState<JsonElement?>>(ResponseState.Loading)
    fun getCategoryChild(categoryId:Int?) = viewModelScope.launch {
        if (networkHelper.isNetworkConnected()) {
            _categoryDataChild.emit(ResponseState.Loading)
            val queryMap = HashMap<String,String>()
            queryMap["id"] = categoryId.toString()
            apiUsesCase.methodeGet("/$API/$CATEGORY_URL",if (categoryId!=null) queryMap else EMPTY_MAP).collect { response->
                _categoryDataChild.emit(response)
            }
        } else{
            _categoryDataChild.emit(ResponseState.Error(NO_INTERNET_CONNECTION))
        }
    }
}