package uz.gxteam.variantmarket.adapters.genericAdapter.holders

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import uz.gxteam.variantmarket.R
import uz.gxteam.variantmarket.databinding.*
import uz.gxteam.variantmarket.models.cateGoryData.CateGoryData
import uz.gxteam.variantmarket.models.filter.FilterData
import uz.gxteam.variantmarket.models.newsData.NewsData
import uz.gxteam.variantmarket.models.searchData.SearchDataAll
import uz.gxteam.variantmarket.models.simpleCategory.Category
import uz.gxteam.variantmarket.models.sliderData.Filial
import uz.gxteam.variantmarket.utils.AppConstant
import uz.gxteam.variantmarket.utils.extensions.*

open class GenericViewHolder<T>(itemView: View): RecyclerView.ViewHolder(itemView),Holder<T>{
    override fun onBind(item: T, position: Int, layoutRes: Int,clickPos:Int,onClikc:(T,position:Int)->Unit) {
        when(layoutRes){
            R.layout.item_search->{
                val itemSearchBinding = ItemSearchBinding.bind(itemView)
                val s = item as String
                itemSearchBinding.textSearch.text = s
            }
            R.layout.item_search_data->{
                val itemSearchData = ItemSearchDataBinding.bind(itemView)
                val searchDataAll = item as SearchDataAll
                itemSearchData.imageData.load(searchDataAll.image){
                    placeholder(R.drawable.plase_holder)
                    crossfade(AppConstant.CROSSFADE_BOOL)
                    crossfade(AppConstant.CROSSFADE)
                    transformations(RoundedCornersTransformation(10f))
                }
                if (searchDataAll.isDelivery) itemSearchData.dataTraining.isVisible = true
                itemSearchData.title.text = searchDataAll.title
                itemSearchData.price.text = searchDataAll.discountSumm.toString()
                itemSearchData.realSumm.text = searchDataAll.allSumm.toString()
                itemSearchData.textPercent.text = searchDataAll.percentText
                itemSearchData.degree.text = searchDataAll.lavel
                itemSearchData.views.text = searchDataAll.users
            }
            R.layout.item_sort->{
                var itemSort = ItemSortBinding.bind(itemView)
                val s = item as String
                itemSort.text.text = s
            }
            R.layout.item_filter->{
                var itemFilter = ItemFilterBinding.bind(itemView)
                val filterData = item as FilterData
                 itemFilter.checkbox.isChecked = filterData.isChecked
                itemFilter.checkbox.text = filterData.name

                itemFilter.checkbox.setOnClickListener {
                    Log.e("Click", item.toString())
                    onClikc.invoke(item,position)
                }
            }
            R.layout.item_category_filter->{
                val binding = ItemCategoryFilterBinding.bind(itemView)
                val filterData = item as FilterData
                binding.apply {
                    categoryTv.textApp(filterData.name)
                }
            }
            R.layout.item_image->{
                val itemImageBinding = ItemImageBinding.bind(itemView)
                val i = item as Int
                itemImageBinding.image.setImageResource(i)
            }
            R.layout.item_rv_product_image->{
                val itemImageBinding = ItemRvProductImageBinding.bind(itemView)
                val i = item as Int
                itemImageBinding.image.setImageResource(i)
                val gradientDrawable =
                    itemImageBinding.consImage.background.mutate() as GradientDrawable
                if (position==clickPos){
                    gradientDrawable.setStroke(3, ContextCompat.getColor(itemView.context,R.color.app_background))
                }else{
                    gradientDrawable.setStroke(3,ContextCompat.getColor(itemView.context,R.color.stroke_color))
                }
            }
            R.layout.item_check->{
                val itemCheckBinding = ItemCheckBinding.bind(itemView)
                val i = item as String
                val drawableCompat =
                    itemCheckBinding.consChip.background.mutate() as GradientDrawable
                if (position==clickPos){
                    drawableCompat.setColor(ContextCompat.getColor(itemView.context,R.color.app_background))
                    drawableCompat.setStroke(2,ContextCompat.getColor(itemView.context,R.color.app_background))
                    itemCheckBinding.chipText.setTextColor(Color.WHITE)
                }else{
                    drawableCompat.setColor(ContextCompat.getColor(itemView.context,R.color.white))
                    drawableCompat.setStroke(2,ContextCompat.getColor(itemView.context,R.color.stroke_color))
                    itemCheckBinding.chipText.setTextColor(ContextCompat.getColor(itemView.context,R.color.stroke_color))
                }
                itemCheckBinding.chipText.textApp(i)
            }
            R.layout.item_check_size->{
                val binding = ItemCheckSizeBinding.bind(itemView)
                val s = item as String
                binding.chipText.text = s
                val drawableCompat =
                    binding.consChip.background.mutate() as GradientDrawable
                if (position==clickPos){
                    drawableCompat.setColor(ContextCompat.getColor(itemView.context,R.color.app_background))
                    drawableCompat.setStroke(2,ContextCompat.getColor(itemView.context,R.color.app_background))
                    binding.chipText.setTextColor(Color.WHITE)
                }else{
                    drawableCompat.setColor(ContextCompat.getColor(itemView.context,R.color.white))
                    drawableCompat.setStroke(2,ContextCompat.getColor(itemView.context,R.color.stroke_color))
                    binding.chipText.setTextColor(ContextCompat.getColor(itemView.context,R.color.stroke_color))
                }
            }
            R.layout.item_other_product->{
                val binding = ItemOtherProductBinding.bind(itemView)
                val filial = item as Filial
                if(filial.isDelivery == true) binding.deliveryView.visible()
                binding.companyName.textApp(filial.name)
                binding.creditSumma.textApp(filial.creditSumma)
                binding.creditMonth.textApp(filial.creditMonth)
                binding.summa.textApp(filial.allSumma)
            }
            R.layout.category_data_item->{
                val binding = CategoryDataItemBinding.bind(itemView)
                val cateGoryData = item as CateGoryData
                binding.textItem.textApp(cateGoryData.title)
                binding.image.dataImage(cateGoryData.image){ isCreate ->
                    if (isCreate) binding.spinKit.gone()
                }
            }
            // TODO: item_category_view
            R.layout.item_category_view->{
                val binding = ItemCategoryViewBinding.bind(itemView)
                val category = item as Category
                binding.imageItem.setImageResource(category.image)
                binding.textItem.textApp(category.name)
                val gradientDrawable = binding.consData.background.mutate() as GradientDrawable
                gradientDrawable.setColor(Color.parseColor(category.color))
                if (clickPos!=position){
                    gradientDrawable.setStroke(2,Color.parseColor(category.color))
                }else{
                    gradientDrawable.setStroke(2,ContextCompat.getColor(itemView.context,R.color.app_background))
                }
            }
            // TODO: ItemCategory
            R.layout.item_category->{
                var binding = ItemCategoryBinding.bind(itemView)
                val category = item as Category
                binding.imageItem.setImageResource(category.image)
                binding.textItem.textApp(category.name)
                binding.cardBtn.setCardBackgroundColor(category.color.colorParse())
            }
            R.layout.item_search_data_discount->{
                val itemSearchData = ItemSearchDataDiscountBinding.bind(itemView)
                val searchDataAll = item as SearchDataAll
                itemSearchData.imageData.dataImage(searchDataAll.image){ isCreate ->
                    if (isCreate) itemSearchData.spinKit.gone()
                }

                if (searchDataAll.discount == true) itemSearchData.discountCard.visible()
                if(searchDataAll.isDelivery) itemSearchData.delivery.visible() else itemSearchData.delivery.gone()
                itemSearchData.title.text = searchDataAll.title
               // itemSearchData.price.text = searchDataAll.discountSumm.toString()
                itemSearchData.degree.text = searchDataAll.lavel
                itemSearchData.views.text = searchDataAll.users
            }
            R.layout.item_new_product->{
                val binding = ItemNewProductBinding.bind(itemView)
                val news = item as NewsData
                binding.image.dataImage(news.image){}
                binding.textMonth.textApp(news.month.toString())
                binding.textProduct.textApp(news.title)
                if (news.discount == true && news.isDelivery == true){
                    binding.cardDiscount.visible()
                }else if (news.discount == true && news.isDelivery == false){
                    binding.iconDelivery.gone()
                    binding.cardDiscount.visible()
                }else if (news.discount == false && news.isDelivery == true){
                    binding.textDiscount.gone()
                    binding.cardDiscount.visible()
                }
            }
        }
    }
}
