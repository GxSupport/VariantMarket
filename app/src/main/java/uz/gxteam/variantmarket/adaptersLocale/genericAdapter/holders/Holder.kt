package uz.gxteam.variantmarket.adaptersLocale.genericAdapter.holders

import androidx.annotation.LayoutRes


interface Holder<T>{
    fun onBind(item:T,position:Int,@LayoutRes layoutRes: Int,clickPos:Int,onClick:(T,position:Int)->Unit)
}