package uz.gxteam.variantmarket.adaptersLocale.adapterAll

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gxteam.variantmarket.databinding.ItemMainBinding
import uz.gxteam.variantmarket.models.local.allM.AllData
import uz.gxteam.variantmarket.models.local.allM.CategoryAll
import uz.gxteam.variantmarket.utils.appConstant.AppConstant.CLIPCHILDREN
import uz.gxteam.variantmarket.utils.appConstant.AppConstant.CLIPTOPADDING

class AllItemAdapter(var onItemClickListener:OnItemClickListener):ListAdapter<CategoryAll,AllItemAdapter.Vh>(MyDiffUtil()) {
    inner class Vh(var itemMainBinding: ItemMainBinding):RecyclerView.ViewHolder(itemMainBinding.root){
     fun onBind(categoryAll: CategoryAll,position: Int){
         var childAllAdapter = ChildAllAdapter(this@AllItemAdapter)
         childAllAdapter.submitList(categoryAll.listItem)

         itemMainBinding.rvItem.adapter = childAllAdapter
         itemMainBinding.title.text = categoryAll.title
         itemMainBinding.rvItem.clipToPadding = CLIPTOPADDING
         itemMainBinding.rvItem.clipChildren = CLIPCHILDREN
         val loadAnimation = AnimationUtils.loadAnimation(itemView.context, android.R.anim.slide_in_left)
         itemView.animation = loadAnimation

         itemMainBinding.categoryTableView.setOnClickListener {
             onItemClickListener.onItemClickAll(categoryAll,position)
         }

     }
    }


    class MyDiffUtil:DiffUtil.ItemCallback<CategoryAll>(){
        override fun areItemsTheSame(oldItem: CategoryAll, newItem: CategoryAll): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CategoryAll, newItem: CategoryAll): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemMainBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(getItem(position),position)

    }

    interface OnItemClickListener{
        fun onItemClick(allData: AllData,position: Int)
        fun onItemClickLike(allData: AllData,position: Int)
        fun onItemClickAll(categoryAll:CategoryAll,position: Int)
    }
}