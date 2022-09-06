package uz.gxteam.variantmarket.adapters.genericAdapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.github.hariprasanths.bounceview.BounceView
import uz.gxteam.variantmarket.R
import uz.gxteam.variantmarket.adapters.genericAdapter.holders.GenericViewHolder
import uz.gxteam.variantmarket.databinding.*
import uz.gxteam.variantmarket.utils.extensions.*

class AdapterGeneric<T:Any>(
    @LayoutRes private val layoutRes: Int,
    var currentResult: ArrayList<T>,
    var onClick:(T,position:Int)->Unit
    ):RecyclerView.Adapter<GenericViewHolder<T>>() {
    private var clickPosition:Int = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<T> {
        var item = LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)
        return GenericViewHolder(item)
    }
    override fun getItemCount(): Int {
        return currentResult.size
    }

    override fun onBindViewHolder(holder: GenericViewHolder<T>, position: Int) {
        holder.onBind(currentResult[position],position,layoutRes,clickPosition,onClick)

        when(layoutRes){
            R.layout.item_search->{
                holder.itemView.animation = AnimationUtils.loadAnimation(holder.itemView.context,R.anim.anim_view)
            }
            R.layout.item_search_data->{
                holder.itemView.animation = loadAnimation(holder.itemView)
            }
            R.layout.category_data_item->{
                holder.itemView.animation = loadAnimation(holder.itemView)
            }
            R.layout.item_category->{
                holder.itemView.animation = AnimationUtils.loadAnimation(holder.itemView.context,R.anim.anim_view)
            }
            R.layout.item_search_data_discount->{
                holder.itemView.animation = loadAnimation(holder.itemView)
            }
            R.layout.item_new_product->{
                holder.itemView.animation = loadAnimation(holder.itemView)
            }
            R.layout.order_item->{
                holder.itemView.animation = loadAnimation(holder.itemView)
            }
            R.layout.bank_card->{
                holder.itemView.animation = AnimationUtils.loadAnimation(holder.itemView.context,R.anim.anim_view)
            }
            R.layout.favorite_item->{
                holder.itemView.animation = loadAnimation(holder.itemView)
            }
            else->{
//                val loadAnimation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.anim_view)
//                holder.itemView.animation = loadAnimation
            }
        }

        holder.itemView.setOnClickListener {
            animationClick(holder.itemView)
            when(layoutRes){
                R.layout.item_search->{
                    onClick.invoke(currentResult[position],position)
                }
                R.layout.item_search_data->{
                    onClick.invoke(currentResult[position],position)
                }
                R.layout.item_sort->{

                }
                R.layout.item_category_filter->{
                    onClick.invoke(currentResult[position],position)
                }
                R.layout.item_rv_product_image->{
                    onClick.invoke(currentResult[position],position)
                }
                R.layout.item_check->{
                    onClick.invoke(currentResult[position],position)
                }
                R.layout.item_check_size->{
                    onClick.invoke(currentResult[position],position)
                }
                R.layout.item_other_product->{
                    onClick.invoke(currentResult[position],position)
                }
                R.layout.category_data_item->{
                    onClick.invoke(currentResult[position],position)
                }
                R.layout.item_category_view->{
                    onClick.invoke(currentResult[position],position)
                }
                R.layout.item_category->{
                    onClick.invoke(currentResult[position],position)
                }
                R.layout.item_search_data_discount->{
                    onClick.invoke(currentResult[position],position)
                }
                R.layout.item_new_product->{
                    onClick.invoke(currentResult[position],position)
                }
                R.layout.order_item->{
                    onClick.invoke(currentResult[position],position)
                }
            }
        }
    }

    fun loadAnimation(itemView:View):Animation{
        return  AnimationUtils.loadAnimation(itemView.context, R.anim.rv_item_anim)
    }


    fun setPositionClick(pos:Int){
        clickPosition = pos
        notifyDataSetChanged()
    }
    fun animationClick(itemView: View){
        BounceView.addAnimTo(itemView)
            .setScaleForPopOutAnim(1.0f, 1.0f);
    }
}

