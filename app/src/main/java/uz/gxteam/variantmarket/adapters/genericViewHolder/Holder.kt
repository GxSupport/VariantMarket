package uz.gxteam.variantmarket.adapters.genericViewHolder

interface Holder {
    fun <T> onBind(
        data:T,
        position:Int,
        layoutRes:Int,
        clickPosition:Int,
        onClick:(data:T,position:Int,clickType:Int)->Unit
    )
}