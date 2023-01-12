package uz.gxteam.variantmarket.presentation.ui.productViewPagerPage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import uz.gxteam.variantmarket.R
import uz.gxteam.variantmarket.adaptersLocale.genericAdapter.AdapterGeneric
import uz.gxteam.variantmarket.databinding.FragmentProductDataBinding
import uz.gxteam.variantmarket.models.local.newsData.NewsData
import uz.gxteam.variantmarket.presentation.ui.base.BaseFragment

private const val ARG_PARAM1 = "param1"

class ProductDataFragment : BaseFragment<FragmentProductDataBinding>() {
    private var category: String? = null

    private lateinit var adapterGeneric: AdapterGeneric<NewsData>
    override fun start(savedInstanceState: Bundle?) {
        arguments?.let {
            category = it.getString(ARG_PARAM1)
        }
    }

    override fun setup(savedInstanceState: Bundle?) {
        binding.apply {
            adapterGeneric = AdapterGeneric(R.layout.item_new_product,getNewsData()){newsData, position ->
                appCompositionRoot.screenNavigate.createDataProductFragmentInCategoryView()
            }
            rvData.adapter = adapterGeneric
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(category: String) =
            ProductDataFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, category)
                }
            }
    }

    override fun inflateViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentProductDataBinding  =
        FragmentProductDataBinding.inflate(inflater,container,false)


}