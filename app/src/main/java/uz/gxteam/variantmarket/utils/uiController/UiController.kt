package uz.gxteam.variantmarket.utils.uiController

interface UiController {
    fun showLoading()
    fun hideLoading()
    fun error(errorCode:Int,message:String,onClick:(isClick:Boolean)->Unit)
}