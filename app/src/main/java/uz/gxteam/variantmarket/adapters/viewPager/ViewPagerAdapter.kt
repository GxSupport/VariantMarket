package uz.gxteam.variantmarket.adapters.viewPager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.gxteam.variantmarket.presentation.ui.productViewPagerPage.ProductDataFragment

class ViewPagerAdapter(var listData:List<String>,fragmentActivity: FragmentActivity):FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return listData.size
    }

    override fun createFragment(position: Int): Fragment {
        return ProductDataFragment.newInstance(listData[position])
    }
}