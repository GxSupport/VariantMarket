package uz.gxteam.variantmarket.presentation.ui.searchView

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import uz.gxteam.variantmarket.R
import uz.gxteam.variantmarket.adapters.genericAdapter.AdapterGeneric
import uz.gxteam.variantmarket.databinding.FragmentSearchBinding
import uz.gxteam.variantmarket.presentation.ui.base.BaseFragment

class SearchFragment : BaseFragment<FragmentSearchBinding>(){
    private lateinit var adapterGeneric: AdapterGeneric<String>

    override fun setup(savedInstanceState: Bundle?) {
        val loadAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.anim_view)
        binding.apply {

            Handler(Looper.getMainLooper()).postDelayed({
                includeSearch.shimmerSearch.visibility = View.GONE
                consView.visibility = View.VISIBLE
            },2000)

            searchInput.requestFocus()
            searchLinear.animation = loadAnimation
            back.setOnClickListener {
                appCompositionRoot.screenNavigate.popBackStack()
            }

            clearInput.setOnClickListener {
                if (searchInput.text.toString().isNotEmpty()){
                    searchInput.text.clear()
                }else{
                    appCompositionRoot.screenNavigate.popBackStack()
                }
            }


            adapterGeneric = AdapterGeneric(R.layout.item_search,getDataSearch()){ s: String, position: Int ->
                val navOptions = NavOptions.Builder()
                    .setEnterAnim(R.anim.enter)
                    .setExitAnim(R.anim.exit)
                    .setPopEnterAnim(R.anim.pop_enter)
                    .setPopExitAnim(R.anim.pop_exit)
                    .build()
                var bundle = Bundle()
                bundle.putString("name",s)
                findNavController().navigate(R.id.action_searchFragment_to_searchedDataFragment,bundle,navOptions)
            }
            rvSearch.adapter = adapterGeneric

        }
    }

    override fun start(savedInstanceState: Bundle?) {

    }
}