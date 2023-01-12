package uz.gxteam.variantmarket.viewModels.mainViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gxteam.variantmarket.models.local.cardData.saveCard.SaveCard
import uz.gxteam.variantmarket.repository.apiRepository.ApiRepositoryImpl
import uz.gxteam.variantmarket.usesCase.apiUsesCase.ApiUsesCase
import uz.gxteam.variantmarket.utils.networkHelper.NetworkHelper
import uz.gxteam.variantmarket.utils.sharedPreferences.MySharedPreferences
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val apiUsesCase: ApiUsesCase,
    private val networkHelper: NetworkHelper,
    private val mySharedPreferences: MySharedPreferences
):ViewModel(){
    // TODO: Myshared
    val myShared get() = mySharedPreferences

    val dataLive:LiveData<SaveCard> get() = _liveDataPos
    private var _liveDataPos:MutableLiveData<SaveCard> = MutableLiveData()

    fun setValuePos(pos:SaveCard){
        _liveDataPos.postValue(pos)
    }


}
