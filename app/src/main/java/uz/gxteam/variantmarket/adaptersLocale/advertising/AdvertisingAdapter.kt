package uz.gxteam.variantmarket.adaptersLocale.advertising

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.hariprasanths.bounceview.BounceView
import uz.gxteam.variantmarket.databinding.ItemSlideBinding
import uz.gxteam.variantmarket.models.local.simpleSlide.SlideData
import uz.gxteam.variantmarket.utils.extensions.dataImage

class AdvertisingAdapter(var listSlide:ArrayList<SlideData>,var onItemClickListener: OnItemClikcListener):RecyclerView.Adapter<AdvertisingAdapter.Vh>() {
    inner class Vh(var itemSlideBinding: ItemSlideBinding):RecyclerView.ViewHolder(itemSlideBinding.root){
        fun onBind(slideData: SlideData,position: Int){
            itemSlideBinding.imageItem.dataImage(slideData.image){}


            itemSlideBinding.buyNow.setOnClickListener {
                onItemClickListener.onItemClick(slideData,position)
            }
            itemView.setOnClickListener {
                onItemClickListener.onItemClick(slideData,position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemSlideBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(listSlide[position],position)
    }

    override fun getItemCount(): Int {
        return listSlide.size
    }

    interface OnItemClikcListener{
        fun onItemClick(slideData: SlideData,position: Int)
    }
    fun animationClick(itemView: View){
        BounceView.addAnimTo(itemView)
            .setScaleForPopOutAnim(1.0f, 1.0f);
    }
}