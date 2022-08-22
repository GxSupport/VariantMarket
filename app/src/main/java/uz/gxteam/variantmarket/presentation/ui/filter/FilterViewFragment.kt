package uz.gxteam.variantmarket.presentation.ui.filter

import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import uz.gxteam.variantmarket.R
import uz.gxteam.variantmarket.adapters.genericAdapter.AdapterGeneric
import uz.gxteam.variantmarket.databinding.FragmentFilterViewBinding
import uz.gxteam.variantmarket.models.filter.FilterData
import uz.gxteam.variantmarket.presentation.ui.base.BaseFragment

class FilterViewFragment : BaseFragment<FragmentFilterViewBinding>() {

    private lateinit var adapterGeneric:AdapterGeneric<FilterData>
    private lateinit var adapterGenericFilterCate:AdapterGeneric<FilterData>


    override fun setup(savedInstanceState: Bundle?) {
        binding.apply {
            val loadAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.anim_view)
            consTool.animation = loadAnimation
//            toolbarText.animation = loadAnimation
//            toolbarText.animation = loadAnimation

            adapterGeneric = AdapterGeneric(R.layout.item_filter,loadFilter()){ filterData, position ->

            }
            rvFilter.adapter = adapterGeneric


            adapterGenericFilterCate =  AdapterGeneric(R.layout.item_category_filter,loadFilter()){ filterData, position ->
                Log.e("ClickRvData", filterData.toString())
            }
            rvCate.adapter = adapterGenericFilterCate

        }
    }

    override fun start(savedInstanceState: Bundle?) {

    }



}