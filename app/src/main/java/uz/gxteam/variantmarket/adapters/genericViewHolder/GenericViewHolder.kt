package uz.gxteam.variantmarket.adapters.genericViewHolder

import android.graphics.drawable.GradientDrawable
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.AnimationUtils.loadAnimation
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import uz.gxteam.variantmarket.BuildConfig.BASE_URL
import uz.gxteam.variantmarket.R
import uz.gxteam.variantmarket.databinding.CategoryDataItemBinding
import uz.gxteam.variantmarket.databinding.ItemCategoryBinding
import uz.gxteam.variantmarket.databinding.ItemCategoryViewBinding
import uz.gxteam.variantmarket.models.categoryModel.Succes
import uz.gxteam.variantmarket.utils.appConstant.AppConstant.DEFAULT_CLICK_TYPE
import uz.gxteam.variantmarket.utils.appConstant.AppConstant.EN
import uz.gxteam.variantmarket.utils.appConstant.AppConstant.RU
import uz.gxteam.variantmarket.utils.appConstant.AppConstant.UZB
import uz.gxteam.variantmarket.utils.extensions.dataImage
import uz.gxteam.variantmarket.utils.extensions.getLanguage
import uz.gxteam.variantmarket.utils.extensions.imageData

class GenericViewHolder<T>(private val itemView:View):RecyclerView.ViewHolder(itemView.rootView),Holder {
    override fun <T> onBind(
        data: T,
        position: Int,
        layoutRes: Int,
        clickPosition:Int,
        onClick: (data: T, position: Int, clickType: Int) -> Unit
    ) {
        when(layoutRes){
            R.layout.item_category->{
                itemCategory(data,position,onClick)
                itemView.animation = loadAnimation(itemView)
            }
            R.layout.item_category_view->{
                itemCategoryData(data,position,clickPosition,onClick)
            }
            R.layout.category_data_item->{
                categoryDataItem(data,position,onClick)
            }
        }
    }

    private fun <T> itemCategory(data:T,position: Int,onClick:(data:T,position:Int,clickType:Int)->Unit){
        val binding = ItemCategoryBinding.bind(itemView)
        if (data is Succes){
            binding.imageItem.imageData("$BASE_URL${data.image}",itemView.context)
            // lang type ru en uz
            val language = getLanguage(itemView.context)
            when(language.lowercase()){
                UZB.lowercase()->{
                    binding.textItem.text = data.name_uz
                }
                RU.lowercase()->{
                    binding.textItem.text = data.name_ru
                }
                EN.lowercase()->{
                    binding.textItem.text = data.name_uzc
                }
            }
           binding.cardBtn.setOnClickListener {
               onClick.invoke(data,position,DEFAULT_CLICK_TYPE)
           }
        }
    }

    private fun <T> itemCategoryData(data:T,position: Int,clickPosition: Int,onClick:(data:T,position:Int,clickType:Int)->Unit){
        val binding = ItemCategoryViewBinding.bind(itemView)
        if (data is Succes){
            binding.imageItem.imageData("$BASE_URL${data.image}",itemView.context)
            val gradientDrawable = binding.consData.background.mutate() as GradientDrawable
            if (position == clickPosition){
                gradientDrawable.setStroke(2,ContextCompat.getColor(itemView.context,R.color.strocke_color))
            } else {
                gradientDrawable.setStroke(2,ContextCompat.getColor(itemView.context,R.color.application_background))
            }
            val loadAnimation = AnimationUtils.loadAnimation(itemView.context, R.anim.rv_anim)
            itemView.animation = loadAnimation
            // lang type ru en uz
            val language = getLanguage(itemView.context)
            when(language.lowercase()){
                UZB.lowercase()->{
                    binding.textItem.text = data.name_uz
                }
                RU.lowercase()->{
                    binding.textItem.text = data.name_ru
                }
                EN.lowercase()->{
                    binding.textItem.text = data.name_uzc
                }
            }
            binding.cardBtn.setOnClickListener {
                onClick.invoke(data,position,DEFAULT_CLICK_TYPE)
            }
        }
    }

    private fun <T> categoryDataItem(data:T,position: Int,onClick:(data:T,position:Int,clickType:Int)->Unit) {
        val binding = CategoryDataItemBinding.bind(itemView)
        if (data is Succes){
            binding.image.imageData("$BASE_URL${data.image}",itemView.context)
            when(getLanguage(itemView.context).lowercase()){
                UZB.lowercase()->{
                    binding.textItem.text = data.name_uz
                }
                RU.lowercase()->{
                    binding.textItem.text = data.name_ru
                }
                EN.lowercase()->{
                    binding.textItem.text = data.name_uzc
                }
            }
          binding.cardData.setOnClickListener {
              onClick.invoke(data,position, DEFAULT_CLICK_TYPE)
          }
        }
    }
    fun loadAnimation(itemView:View): Animation {
        return  AnimationUtils.loadAnimation(itemView.context, R.anim.rv_item_anim)
    }
}