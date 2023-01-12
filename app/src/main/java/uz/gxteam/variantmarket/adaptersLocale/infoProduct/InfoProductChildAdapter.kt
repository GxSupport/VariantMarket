package uz.gxteam.variantmarket.adaptersLocale.infoProduct

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.gxteam.variantmarket.databinding.ItemCharChildBinding
import uz.gxteam.variantmarket.models.local.sliderData.InfoProduct
import uz.gxteam.variantmarket.utils.extensions.textApp

class InfoProductChildAdapter(
    var list: List<InfoProduct>
):RecyclerView.Adapter<InfoProductChildAdapter.Vh>() {
    inner class Vh(var itemCharChildBinding: ItemCharChildBinding):RecyclerView.ViewHolder(itemCharChildBinding.root){
        fun onBind(infoProduct: InfoProduct,position: Int){
            itemCharChildBinding.infoTv.textApp(infoProduct.info)
            itemCharChildBinding.titleTv.textApp(infoProduct.title)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemCharChildBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position],position)
    }

    override fun getItemCount(): Int {
     return list.size
    }
}