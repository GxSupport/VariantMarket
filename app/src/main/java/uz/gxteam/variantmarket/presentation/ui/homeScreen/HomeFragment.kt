package uz.gxteam.variantmarket.presentation.ui.homeScreen


import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.viewpager2.widget.ViewPager2
import uz.gxteam.variantmarket.R
import uz.gxteam.variantmarket.adapters.viewPagerAdapter.SlideAdapter
import uz.gxteam.variantmarket.databinding.FragmentHomeBinding
import uz.gxteam.variantmarket.presentation.activitys.MainActivity
import uz.gxteam.variantmarket.presentation.ui.base.BaseFragment
import uz.gxteam.variantmarket.utils.AppConstant.ONE
import uz.gxteam.variantmarket.utils.AppConstant.THREE
import uz.gxteam.variantmarket.utils.AppConstant.TWO
import uz.gxteam.variantmarket.utils.AppConstant.ZERO
import uz.gxteam.variantmarket.utils.uiController.UiController

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private lateinit var slideAdapter:SlideAdapter
    override fun setup(savedInstanceState: Bundle?) {
        binding.apply {
            slideAdapter = SlideAdapter(requireActivity())
            viewPager2.adapter = slideAdapter
            viewPager2.isUserInputEnabled = false

            Handler(Looper.getMainLooper()).postDelayed({
                appCompositionRoot.viewPager2 = viewPager2
            },1000)

            viewPager2.registerOnPageChangeCallback(object:ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    when(position){
                        ZERO->{
                            bottomNavigation.menu.findItem(R.id.nav_home).isChecked = true
                        }
                        ONE->{
                            bottomNavigation.menu.findItem(R.id.nav_gallery).isChecked = true
                        }
                        TWO->{
                            bottomNavigation.menu.findItem(R.id.nav_home1).isChecked = true
                        }
                        THREE->{
                            bottomNavigation.menu.findItem(R.id.nav_gallery1).isChecked = true
                        }
                    }
                }
            })
            bottomNavigation.setOnItemSelectedListener {
                when(it.itemId){
                    R.id.nav_home->{
                        viewPager2.currentItem = ZERO
                        toolbarTitle(appCompositionRoot.activityApp.getString(R.string.mainPage))
                    }
                    R.id.nav_gallery->{
                        viewPager2.currentItem = ONE
                        toolbarTitle(appCompositionRoot.activityApp.getString(R.string.category))
                    }
                    R.id.nav_home1->{
                        viewPager2.currentItem = TWO
                        toolbarTitle(appCompositionRoot.activityApp.getString(R.string.orders))
                    }
                    R.id.nav_gallery1->{
                        viewPager2.currentItem = THREE
                        toolbarTitle(appCompositionRoot.activityApp.getString(R.string.profil))
                    }
                }
                true
            }

        }
    }


    override fun start(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
    }


    fun toolbarTitle(title:String){
        (activity as MainActivity).supportActionBar?.title = title
    }

}