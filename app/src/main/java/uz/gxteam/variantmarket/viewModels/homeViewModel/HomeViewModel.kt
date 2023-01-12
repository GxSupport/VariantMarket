package uz.gxteam.variantmarket.viewModels.homeViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonElement
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uz.gxteam.variantmarket.models.categoryModel.CategoryModel
import uz.gxteam.variantmarket.repository.apiRepository.ApiRepositoryImpl
import uz.gxteam.variantmarket.usesCase.apiUsesCase.ApiUsesCase
import uz.gxteam.variantmarket.utils.appConstant.AppConstant.API
import uz.gxteam.variantmarket.utils.appConstant.AppConstant.CATEGORY_URL
import uz.gxteam.variantmarket.utils.appConstant.AppConstant.EMPTY_MAP
import uz.gxteam.variantmarket.utils.appConstant.AppConstant.NO_INTERNET_CONNECTION
import uz.gxteam.variantmarket.utils.networkHelper.NetworkHelper
import uz.gxteam.variantmarket.utils.responseState.ResponseState
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val networkHelper: NetworkHelper,
    private val apiUsesCase:ApiUsesCase
):ViewModel() {
    val categoryData:StateFlow<ResponseState<ArrayList<JsonElement?>>> get() = _categoryData
    private val _categoryData = MutableStateFlow<ResponseState<ArrayList<JsonElement?>>>(ResponseState.Loading)
    fun getCategory() = viewModelScope.launch {
        if (networkHelper.isNetworkConnected()){
            _categoryData.emit(ResponseState.Loading)
            // query category
//            val queryCategory = HashMap<String,String>()
//            queryCategory["id"] = "1"
            apiUsesCase.getHomeAllData("/$API/$CATEGORY_URL", EMPTY_MAP).collect { response->
                    _categoryData.emit(response)
            }
        } else {
            _categoryData.emit(ResponseState.Error(NO_INTERNET_CONNECTION))
        }
    }
}