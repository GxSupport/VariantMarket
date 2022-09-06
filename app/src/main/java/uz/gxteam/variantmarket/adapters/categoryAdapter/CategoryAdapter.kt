package uz.gxteam.variantmarket.adapters.categoryAdapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gxteam.variantmarket.R
import uz.gxteam.variantmarket.databinding.ItemCategoryBinding
import uz.gxteam.variantmarket.models.local.simpleCategory.Category


class CategoryAdapter:ListAdapter<Category,CategoryAdapter.Vh>(MyDiffUtill()) {
    inner class Vh(var itemCategoryBinding:ItemCategoryBinding):RecyclerView.ViewHolder(itemCategoryBinding.root){
        fun onBind(category: Category,position: Int){
            itemCategoryBinding.imageItem.setImageResource(category.image)
            itemCategoryBinding.textItem.text = category.name
            itemCategoryBinding.cardBtn.setCardBackgroundColor(Color.parseColor(category.color))
            val loadAnimation = AnimationUtils.loadAnimation(itemView.context, R.anim.rv_anim)
            itemView.animation = loadAnimation
        }
    }

    class MyDiffUtill:DiffUtil.ItemCallback<Category>(){
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.image == newItem.image
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemCategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(getItem(position),position)
    }
    interface OnItemClickListener{
        fun onItemClick(category: Category,position: Int)
    }
}