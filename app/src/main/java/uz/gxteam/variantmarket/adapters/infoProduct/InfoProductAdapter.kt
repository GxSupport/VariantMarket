package uz.gxteam.variantmarket.adapters.infoProduct

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.gxteam.variantmarket.databinding.ItemCharacteristicsBinding
import uz.gxteam.variantmarket.models.local.sliderData.InfoData
import uz.gxteam.variantmarket.utils.extensions.textApp

class InfoProductAdapter(
    var mapInfoData:List<InfoData>
):RecyclerView.Adapter<InfoProductAdapter.Vh>() {
    inner class Vh(var itemCharacteristicsBinding: ItemCharacteristicsBinding):RecyclerView.ViewHolder(itemCharacteristicsBinding.root){
       fun onBind(infoData: InfoData,position: Int){
           itemCharacteristicsBinding.title.textApp(infoData.title)
           var infoProductChildAdapter = InfoProductChildAdapter(infoData.list)
           itemCharacteristicsBinding.rvData.adapter = infoProductChildAdapter
       }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemCharacteristicsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
      holder.onBind(mapInfoData[position],position)
    }

    override fun getItemCount(): Int {
        return mapInfoData.size
    }
}