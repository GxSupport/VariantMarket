package uz.gxteam.variantmarket.adapters.viewPagerAdapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.gxteam.variantmarket.presentation.ui.favorites.FavoritesFragment
import uz.gxteam.variantmarket.presentation.ui.homeScreen.view.MainScreenFragment
import uz.gxteam.variantmarket.presentation.ui.homeScreen.view.category.CategoryFragment
import uz.gxteam.variantmarket.presentation.ui.homeScreen.view.orders.OrdersFragment
import uz.gxteam.variantmarket.presentation.ui.homeScreen.view.profil.ProfilFragment
import uz.gxteam.variantmarket.utils.AppConstant.FOUR
import uz.gxteam.variantmarket.utils.AppConstant.ONE
import uz.gxteam.variantmarket.utils.AppConstant.THREE
import uz.gxteam.variantmarket.utils.AppConstant.TWO
import uz.gxteam.variantmarket.utils.AppConstant.ZERO

class SlideAdapter(fragmentActivity:FragmentActivity):FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return FOUR
    }

    override fun createFragment(position: Int): Fragment {
       return when(position){
            ZERO->{
                MainScreenFragment()
            }
            ONE->{
                CategoryFragment()
            }
            TWO->{
                FavoritesFragment()
            }
            THREE->{
                OrdersFragment()
               // ProfilFragment()
            }
           else -> {
               MainScreenFragment()
           }
        }
    }
}