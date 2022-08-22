package uz.gxteam.variantmarket.adapters.discount

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
import uz.gxteam.variantmarket.databinding.ItemDiscountBinding
import uz.gxteam.variantmarket.models.discount.Discount
import uz.gxteam.variantmarket.utils.AppConstant.CROSSFADE

class DiscountAdapter(var onItemClickListener: OnItemClickListener):ListAdapter<Discount,DiscountAdapter.Vh>(MyDiffUtill()) {
    inner class Vh(var itemDiscountBinding: ItemDiscountBinding):RecyclerView.ViewHolder(itemDiscountBinding.root){
        fun onBind(discount: Discount,position:Int){
            itemDiscountBinding.image.load(discount.image){
                placeholder(R.drawable.plase_holder)
                crossfade(true)
                crossfade(CROSSFADE)
                transformations(RoundedCornersTransformation(20f))
            }
            itemDiscountBinding.textDiscount.text = discount.title

            itemView.setOnClickListener {
                animationClick(itemView)
                onItemClickListener.onDiscountClick(discount,position)
            }
            val loadAnimation = AnimationUtils.loadAnimation(itemView.context, android.R.anim.slide_in_left)
            itemView.animation = loadAnimation
        }
    }

    class MyDiffUtill:DiffUtil.ItemCallback<Discount>(){
        override fun areItemsTheSame(oldItem: Discount, newItem: Discount): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Discount, newItem: Discount): Boolean {
            return oldItem.image == newItem.image
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemDiscountBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(getItem(position),position)
    }

    interface OnItemClickListener{
        fun  onDiscountClick(discount: Discount,position: Int)
    }
    fun animationClick(itemView: View){
        BounceView.addAnimTo(itemView)
            .setScaleForPopOutAnim(1.0f, 1.0f);
    }
}