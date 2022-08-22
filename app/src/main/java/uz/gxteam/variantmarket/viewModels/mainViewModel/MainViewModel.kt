package uz.gxteam.variantmarket.viewModels.mainViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel():ViewModel(){
    val dataLive:LiveData<Int> get() = _liveDataPos
    private var _liveDataPos:MutableLiveData<Int> = MutableLiveData()

    fun setValuePos(pos:Int){
        _liveDataPos.postValue(pos)
    }
}
