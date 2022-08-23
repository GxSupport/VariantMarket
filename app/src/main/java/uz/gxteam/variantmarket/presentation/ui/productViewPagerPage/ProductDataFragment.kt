package uz.gxteam.variantmarket.presentation.ui.productViewPagerPage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.FragmentNavigatorExtras
import uz.gxteam.variantmarket.R
import uz.gxteam.variantmarket.adapters.genericAdapter.AdapterGeneric
import uz.gxteam.variantmarket.databinding.FragmentProductDataBinding
import uz.gxteam.variantmarket.models.newsData.NewsData
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




}