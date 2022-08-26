package uz.gxteam.variantmarket.viewModels.mainViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gxteam.variantmarket.models.cardData.saveCard.SaveCard
import uz.gxteam.variantmarket.network.repository.ApiRepository
import uz.gxteam.variantmarket.utils.networkHelper.NetworkHelper
import uz.gxteam.variantmarket.utils.sharedPreferences.MySharedPreferences
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val apiRepository: ApiRepository,
    private val networkHelper: NetworkHelper,
    private val mySharedPreferences: MySharedPreferences
):ViewModel(){
    val dataLive:LiveData<SaveCard> get() = _liveDataPos
    private var _liveDataPos:MutableLiveData<SaveCard> = MutableLiveData()

    fun setValuePos(pos:SaveCard){
        _liveDataPos.postValue(pos)
    }
}
