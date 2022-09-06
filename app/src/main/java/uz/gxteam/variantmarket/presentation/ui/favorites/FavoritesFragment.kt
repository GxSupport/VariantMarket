package uz.gxteam.variantmarket.presentation.ui.favorites

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import uz.gxteam.variantmarket.R
import uz.gxteam.variantmarket.adapters.genericAdapter.AdapterGeneric
import uz.gxteam.variantmarket.databinding.FragmentFavoritesBinding
import uz.gxteam.variantmarket.models.local.newsData.NewsData
import uz.gxteam.variantmarket.presentation.ui.base.BaseFragment
import uz.gxteam.variantmarket.utils.extensions.gone
import uz.gxteam.variantmarket.utils.extensions.visible


class FavoritesFragment : BaseFragment<FragmentFavoritesBinding>() {
    private lateinit var adapterGeneric: AdapterGeneric<NewsData>
    override fun setup(savedInstanceState: Bundle?) {
        menuIconHome()
        toolbarShow()
        binding.apply {
            includeShimmer.shimmer.startShimmer()

            Handler(Looper.getMainLooper()).postDelayed({
                includeShimmer.shimmer.stopShimmer()
                includeShimmer.shimmer.gone()
                rvFavorites.visible()
            },1500)

            adapterGeneric = AdapterGeneric(R.layout.favorite_item,getNewsData()){ newsData, position ->
                appCompositionRoot.screenNavigate.createDataProductInFavorites()
            }
            rvFavorites.adapter = adapterGeneric
        }
    }

    override fun start(savedInstanceState: Bundle?) {

    }
}