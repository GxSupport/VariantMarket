package uz.gxteam.variantmarket.adapters.genericAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import uz.gxteam.variantmarket.adapters.genericDiffUtil.GenericDiffUtil
import uz.gxteam.variantmarket.adapters.genericViewHolder.GenericViewHolder

class GenericAdapter <T> (
    private val layoutRes:Int,
    private val onClick:(data:T,position:Int,clickType:Int)->Unit
):ListAdapter<T,GenericViewHolder<T>>(GenericDiffUtil<T>()) {
    var clickPosition = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<T> {
        val itemView = LayoutInflater.from(parent.context).inflate(layoutRes,parent,false)
        return GenericViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GenericViewHolder<T>, position: Int) {
        holder.onBind(getItem(position),position,layoutRes,clickPosition,onClick)
    }
    fun setPositionClick(pos:Int){
        clickPosition = pos
        notifyDataSetChanged()
    }
}