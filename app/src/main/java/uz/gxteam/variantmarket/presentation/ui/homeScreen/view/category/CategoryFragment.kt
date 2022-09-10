package uz.gxteam.variantmarket.presentation.ui.homeScreen.view.category


import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import uz.gxteam.variantmarket.R
import uz.gxteam.variantmarket.adapters.genericAdapter.AdapterGeneric
import uz.gxteam.variantmarket.databinding.FragmentCategoryBinding
import uz.gxteam.variantmarket.databinding.FragmentFilterViewBinding
import uz.gxteam.variantmarket.models.local.cateGoryData.CateGoryData
import uz.gxteam.variantmarket.models.local.simpleCategory.Category
import uz.gxteam.variantmarket.presentation.ui.base.BaseFragment
import uz.gxteam.variantmarket.utils.AppConstant.OB_POS
import uz.gxteam.variantmarket.utils.extensions.gone
import uz.gxteam.variantmarket.utils.extensions.visible


class CategoryFragment : BaseFragment<FragmentCategoryBinding>() {
    private lateinit var adapterGeneric: AdapterGeneric<CateGoryData>
    private lateinit var adapterGenericCategory: AdapterGeneric<Category>
    private lateinit var listCategory:ArrayList<Category>

    override fun setup(savedInstanceState: Bundle?) {
        binding.apply {
            includeCategory.shimmerCategory.startShimmer()
            includeCategory.shimmerCategory.visible()
            rvCategory.gone()
            rvCategoryData.gone()
            Handler(Looper.getMainLooper()).postDelayed({
                includeCategory.shimmerCategory.stopShimmer()
                includeCategory.shimmerCategory.gone()
                rvCategory.visible()
                rvCategoryData.visible()
            },1500)

            // TODO: Category
            adapterGenericCategory = AdapterGeneric(R.layout.item_category_view,loadCategoryData()){category, position ->
                adapterGenericCategory.setPositionClick(position)
                when(position){
                    0->{
                        adapterGeneric.currentResult = loadCateGoryDataView()
                        adapterGeneric.notifyDataSetChanged()
                    }
                    1->{
                        adapterGeneric.currentResult = loadCateGoryDataViewElectronic()
                        adapterGeneric.notifyDataSetChanged()
                    }
                    2->{
                        adapterGeneric.currentResult = loadCateGoryDataViewElectronic()
                        adapterGeneric.notifyDataSetChanged()
                    }
                    3->{
                        adapterGeneric.currentResult = loadCateGoryDataView()
                        adapterGeneric.notifyDataSetChanged()
                    }
                    4->{
                        adapterGeneric.currentResult = loadCateGoryDataViewElectronic()
                        adapterGeneric.notifyDataSetChanged()
                    }
                }
            }
            rvCategory.adapter = adapterGenericCategory
            // TODO: CategoryData
            adapterGeneric = AdapterGeneric(R.layout.category_data_item,loadCateGoryDataView()){cateGoryData, position ->
                appCompositionRoot.screenNavigate.createDataProductFragment(cateGoryData.title,OB_POS)
            }
            rvCategoryData.adapter = adapterGeneric
        }
    }

    override fun start(savedInstanceState: Bundle?) {

    }

    fun loadCategoryData():ArrayList<Category> {
        listCategory = ArrayList()
        listCategory.add(
            Category(
                name = "Fashion",
                image = R.drawable.ic_shirt,
                color = "#3C2DD4BF"
            )
        )
        listCategory.add(
            Category(
                name = "Electronics",
                image = R.drawable.ic_electron,
                color = "#5BFB923C"
            )
        )
        listCategory.add(
            Category(
                name = "Appliances",
                image = R.drawable.ic_appliances,
                color = "#3E34D399"
            )
        )
        listCategory.add(
            Category(
                name = "Beauty",
                image = R.drawable.ic_pamad,
                color = "#4122D3EE"
            )
        )
        listCategory.add(
            Category(
                name = "Furniture",
                image = R.drawable.ic_furniture,
                color = "#3E3B82F6"
            )
        )
        return listCategory
    }
    fun loadCateGoryDataView():ArrayList<CateGoryData>{
        var listData = ArrayList<CateGoryData>()
        listData.add(CateGoryData("Mans","https://image.shutterstock.com/image-photo/casually-handsome-confident-young-man-260nw-439433326.jpg"))
        listData.add(CateGoryData("Women's","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ-ZHqWA3ajb0g2TmGMYzSoRpiR5HqjelAKfw&usqp=CAU"))
        listData.add(CateGoryData("Kids","https://thumbs.dreamstime.com/b/children-kids-diversity-friendship-happiness-cheerful-concept-56679027.jpg"))
        listData.add(CateGoryData("Children","https://media.istockphoto.com/photos/beautiful-happy-boy-with-painted-hands-picture-id1207261035?k=20&m=1207261035&s=612x612&w=0&h=aEzfrUNuXjGHGhLa0Eet4yGHzsFu3BGsD1W8xu_2UJM="))
        listData.add(CateGoryData("Mans","https://image.shutterstock.com/image-photo/casually-handsome-confident-young-man-260nw-439433326.jpg"))
        listData.add(CateGoryData("Mans","https://image.shutterstock.com/image-photo/casually-handsome-confident-young-man-260nw-439433326.jpg"))
    return listData
    }


    fun loadCateGoryDataViewElectronic():ArrayList<CateGoryData>{
        var listData = ArrayList<CateGoryData>()
        listData.add(CateGoryData("Campyuter","https://cdn.macbro.uz/macbro/ad319481-63dc-4bc2-bf4d-7a6c7aec02f5"))
        listData.add(CateGoryData("Phone","https://images.unsplash.com/photo-1511707171634-5f897ff02aa9?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8cGhvbmV8ZW58MHx8MHx8&w=1000&q=80"))
        listData.add(CateGoryData("Kids","https://thumbs.dreamstime.com/b/children-kids-diversity-friendship-happiness-cheerful-concept-56679027.jpg"))
        listData.add(CateGoryData("I watch","https://techno.uz/image/cache/catalog/w42sb-sbbk-gallery-2-700x500.jpg"))
        listData.add(CateGoryData("Air pods","https://kattabozor.s3.eu-central-1.amazonaws.com/ri/fa205fe513f10e5d952a3885228c22b8fc169ff08387fad36ef9ba5166d32e5f_0YTlio_640l.jpg"))
        listData.add(CateGoryData("Mouse","https://i.dell.com/is/image/DellContent//content/dam/ss2/product-images/peripherals/input-devices/dell/mouse/wm126/dell-mouse-wm126-504x350.jpg?fmt=jpg"))
        return listData
    }

    override fun inflateViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentCategoryBinding =
        FragmentCategoryBinding.inflate(inflater,container,false)
}