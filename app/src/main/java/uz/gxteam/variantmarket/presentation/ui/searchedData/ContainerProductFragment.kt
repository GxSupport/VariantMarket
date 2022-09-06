package uz.gxteam.variantmarket.presentation.ui.searchedData

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import uz.gxteam.variantmarket.R
import uz.gxteam.variantmarket.adapters.genericAdapter.AdapterGeneric
import uz.gxteam.variantmarket.databinding.BottomSheetDialogBinding
import uz.gxteam.variantmarket.databinding.FragmentContainerProductBinding
import uz.gxteam.variantmarket.models.local.searchData.SearchDataAll
import uz.gxteam.variantmarket.presentation.ui.base.BaseFragment
import uz.gxteam.variantmarket.utils.AppConstant.PRODUCT_CATEGORY
import uz.gxteam.variantmarket.utils.extensions.createData
import uz.gxteam.variantmarket.utils.extensions.gone
import uz.gxteam.variantmarket.utils.extensions.visible


private const val NAME_DATA = "name"
private const val POS = "pos"
class ContainerProductFragment : BaseFragment<FragmentContainerProductBinding>() {
    private var nameData: String? = null
    private var pos:Int? = null
    private lateinit var adapterGeneric: AdapterGeneric<SearchDataAll>
    private lateinit var adapterGenericDiscount: AdapterGeneric<SearchDataAll>
    private lateinit var adapterGenericSort: AdapterGeneric<String>

    override fun setup(savedInstanceState: Bundle?) {
        binding.apply {
            binding.includeData.shimmer.startShimmer()
            binding.includeData.shimmerCons.visible()

            Handler(Looper.getMainLooper()).postDelayed({
                binding.includeData.shimmer.gone()
                binding.includeData.shimmer.stopShimmer()
                includeData.shimmer.gone()
                consView.visible()
            },1500)

            // TODO: PopBackStack
            back.setOnClickListener {
                findNavController().popBackStack()
            }
            // TODO: Toolbar text
            toolbarText.text = nameData
            var loadAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.anim_view)
            consToolbar.animation = loadAnimation
            countItems.animation = loadAnimation

            // itemClick
            when(pos){
                0->{
                    adapterGeneric = AdapterGeneric(R.layout.item_search_data,loadListData()){ searchDataAll, position ->
                        appCompositionRoot.screenNavigate.createDataProduct()
                    }
                    rvItems.adapter = adapterGeneric
                }
                2->{
                    adapterGenericDiscount = AdapterGeneric(R.layout.item_search_data_discount,loadListData()){ searchDataAll, position ->
                        appCompositionRoot.screenNavigate.createDataProduct()
                    }
                    rvItems.adapter = adapterGenericDiscount
                }
                PRODUCT_CATEGORY->{
                    adapterGenericDiscount = AdapterGeneric(R.layout.item_search_data_discount,loadListDataProduct()){ searchDataAll, position ->
                        appCompositionRoot.screenNavigate.createDataProduct()
                    }
                    rvItems.adapter = adapterGenericDiscount
                }
            }


            filter.setOnClickListener {
                appCompositionRoot.screenNavigate.createFilterView()
            }



            sort.setOnClickListener {
                var bottomSheet = BottomSheetDialog(requireContext(), R.style.BottomSheetDialogThem)
                bottomSheet.createData(R.layout.bottom_sheet_dialog){ viewBinding->
                    val bottomSheetDialogBinding = viewBinding as BottomSheetDialogBinding
                    adapterGenericSort = AdapterGeneric(R.layout.item_sort,loadSort()){ s: String, position: Int ->

                    }
                    bottomSheetDialogBinding.rvSort.adapter = adapterGenericSort
                }
            }

        }
    }

    override fun start(savedInstanceState: Bundle?) {
        arguments?.let {
            nameData = it.getString(NAME_DATA)
            pos = it.getInt(POS)
        }
    }
}