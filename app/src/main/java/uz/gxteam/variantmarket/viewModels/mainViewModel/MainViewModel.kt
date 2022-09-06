package uz.gxteam.variantmarket.viewModels.mainViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.viewpager2.widget.ViewPager2
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uz.gxteam.variantmarket.models.local.cardData.saveCard.SaveCard
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
    // TODO: Myshared
    val myShared get() = mySharedPreferences

    val dataLive:LiveData<SaveCard> get() = _liveDataPos
    private var _liveDataPos:MutableLiveData<SaveCard> = MutableLiveData()

    fun setValuePos(pos:SaveCard){
        _liveDataPos.postValue(pos)
    }


}
