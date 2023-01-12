package uz.gxteam.variantmarket.adaptersLocale.adapterAll

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.github.hariprasanths.bounceview.BounceView
import uz.gxteam.variantmarket.R
import uz.gxteam.variantmarket.databinding.ItemAllChildBinding
import uz.gxteam.variantmarket.models.local.allM.AllData
import uz.gxteam.variantmarket.utils.appConstant.AppConstant.CHILD_ITEM_IMAGE_RAD
import uz.gxteam.variantmarket.utils.appConstant.AppConstant.CROSSFADE
import uz.gxteam.variantmarket.utils.appConstant.AppConstant.CROSSFADE_BOOL
import uz.gxteam.variantmarket.utils.appConstant.AppConstant.ZERO


class ChildAllAdapter(var allItemAdapter: AllItemAdapter):ListAdapter<AllData,ChildAllAdapter.Vh>(MyDiffUtil()) {
    inner class Vh(var itemAllChildBinding: ItemAllChildBinding):RecyclerView.ViewHolder(itemAllChildBinding.root){
        fun onBind(allData: AllData,position: Int){
            itemAllChildBinding.imageItem.load(allData.image){
                placeholder(R.drawable.plase_holder)
                crossfade(CROSSFADE_BOOL)
                crossfade(CROSSFADE)
                transformations(RoundedCornersTransformation(CHILD_ITEM_IMAGE_RAD))
            }

            itemView.setOnClickListener {
                animationClick(itemView)
                allItemAdapter.onItemClickListener.onItemClick(allData,position)
            }
            val loadAnimation = AnimationUtils.loadAnimation(itemView.context, R.anim.rv_anim)
            itemView.animation = loadAnimation
            if(position==ZERO){
                itemAllChildBinding.textTop.visibility = View.VISIBLE
            }
            itemAllChildBinding.imageLike.setOnClickListener {
                animationClick(itemAllChildBinding.imageLike)
                itemAllChildBinding.imageLikeData.setImageResource(R.drawable.ic_like_1)
                allItemAdapter.onItemClickListener.onItemClickLike(allData,position)
            }
        }
    }

    class MyDiffUtil:DiffUtil.ItemCallback<AllData>(){
        override fun areItemsTheSame(oldItem: AllData, newItem: AllData): Boolean {
           return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: AllData, newItem: AllData): Boolean {
           return oldItem.image == newItem.image
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemAllChildBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(getItem(position),position)
    }

    fun animationClick(itemView: View){
        BounceView.addAnimTo(itemView)
            .setScaleForPopOutAnim(1.0f, 1.0f);
    }
}
