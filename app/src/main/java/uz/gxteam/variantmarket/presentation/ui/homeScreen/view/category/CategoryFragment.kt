package uz.gxteam.variantmarket.presentation.ui.homeScreen.view.category


import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import uz.gxteam.variantmarket.R
import uz.gxteam.variantmarket.adapters.genericAdapter.GenericAdapter
import uz.gxteam.variantmarket.adaptersLocale.genericAdapter.AdapterGeneric
import uz.gxteam.variantmarket.databinding.FragmentCategoryBinding
import uz.gxteam.variantmarket.models.categoryModel.CategoryModel
import uz.gxteam.variantmarket.models.categoryModel.Succes
import uz.gxteam.variantmarket.models.local.cateGoryData.CateGoryData
import uz.gxteam.variantmarket.models.local.simpleCategory.Category
import uz.gxteam.variantmarket.presentation.ui.base.BaseFragment
import uz.gxteam.variantmarket.utils.appConstant.AppConstant.OB_POS
import uz.gxteam.variantmarket.utils.extensions.gone
import uz.gxteam.variantmarket.utils.extensions.logData
import uz.gxteam.variantmarket.utils.extensions.parseClass
import uz.gxteam.variantmarket.utils.extensions.visible
import uz.gxteam.variantmarket.utils.responseState.ResponseState
import uz.gxteam.variantmarket.viewModels.categoryDataVm.CategoryDataViewModel

@AndroidEntryPoint
class CategoryFragment : BaseFragment<FragmentCategoryBinding>() {
    // cateGory data viewModel
    private val categoryDataViewModel:CategoryDataViewModel by viewModels()
    // category id
    private var categoryId = 0
    // generic adapter category
    private val adapterGenericCategory: GenericAdapter<Succes> by lazy {
        GenericAdapter(R.layout.item_category_view){ data, position, clickType ->
            adapterGenericCategory.setPositionClick(position)
            categoryId = data.id
            getCategoryIdChild(categoryId)
        }
    }


    private val categoryAdapterSecond:GenericAdapter<Succes> by lazy {
        GenericAdapter(R.layout.category_data_item){ data, position, clickType ->

        }
    }

    override fun setup(savedInstanceState: Bundle?) {
        binding.apply {
            categoryData()
            binding.rvCategoryData.adapter = categoryAdapterSecond
            // swipe refresh color
            binding.refreshLayout.setColorSchemeColors(ContextCompat.getColor(requireContext(),R.color.strocke_color))
        }
    }

    private fun categoryData() {
        categoryDataViewModel.getCategory(null)
        lifecycleScope.launchWhenCreated {
            categoryDataViewModel.categoryData.collect { result->
                when(result){
                    is ResponseState.Loading->{
                       binding.includeCategory.shimmerCategory.visible()
                    }
                    is ResponseState.Success->{
                       binding.includeCategory.shimmerCategory.gone()
                        // category adapter
                        val categoryModel = result.data?.parseClass(CategoryModel::class.java)
                        adapterGenericCategory.submitList(categoryModel?.success?: emptyList())
                        binding.rvCategory.adapter = adapterGenericCategory
                        binding.rvCategory.setItemViewCacheSize(categoryModel?.success?.size?:0)
                        categoryId = categoryModel?.success?.get(0)?.id?:0
                        getCategoryIdChild(categoryId)
                        // swipe refresh category
                        binding.refreshLayout.setOnRefreshListener {
                            getCategoryIdChild(categoryId)
                        }
                    }
                    is ResponseState.Error->{
                        binding.includeCategory.shimmerCategory.gone()
                        appCompositionRoot.errorDialog(result.errorCode?:0,result.errorMessage.toString()){
                            if (it) categoryData()
                        }
                    }
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun getCategoryIdChild(categoryId:Int){
        categoryDataViewModel.getCategoryChild(categoryId)
        lifecycleScope.launchWhenCreated {
            categoryDataViewModel.categoryDataChild.collect { result->
                when(result){
                    is ResponseState.Loading->{
                       binding.includeCategory.shimmerCategory.visible()
                       binding.rvCategoryData.gone()
                       if (binding.linearNoData.isVisible){
                           binding.linearNoData.gone()
                       }
                    }
                    is ResponseState.Success->{
                        binding.includeCategory.shimmerCategory.gone()
                        binding.rvCategoryData.visible()
                        binding.refreshLayout.isRefreshing = false
                        // category adapter
                        val categoryModel = result.data?.parseClass(CategoryModel::class.java)
                        logData(categoryModel?.success.toString())

                        if (categoryModel?.success?.isEmpty() == true){
                            binding.linearNoData.visible()
                            binding.rvCategoryData.gone()
                        } else {
                            binding.linearNoData.gone()
                            binding.rvCategoryData.visible()
                            categoryAdapterSecond.submitList(categoryModel?.success?: emptyList())
                            binding.rvCategoryData.setItemViewCacheSize(categoryModel?.success?.size?:0)
                        }
                    }
                    is ResponseState.Error->{
                        binding.includeCategory.shimmerCategory.gone()
                        appCompositionRoot.errorDialog(result.errorCode?:0,result.errorMessage.toString()){
                            if (it) categoryData()
                        }
                    }
                }
            }
        }
    }

    override fun start(savedInstanceState: Bundle?) {

    }

    override fun inflateViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentCategoryBinding =
        FragmentCategoryBinding.inflate(inflater,container,false)
}