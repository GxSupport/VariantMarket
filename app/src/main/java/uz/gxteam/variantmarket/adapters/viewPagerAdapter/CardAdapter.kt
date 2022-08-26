package uz.gxteam.variantmarket.adapters.viewPagerAdapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.gxteam.variantmarket.models.cardData.saveCard.SaveCard
import uz.gxteam.variantmarket.presentation.ui.create_card.CardItemDataFragment
import uz.gxteam.variantmarket.utils.AppConstant.FOUR

class CardAdapter(fragmentActivity:FragmentActivity,private val saveCard:SaveCard):FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return FOUR
    }

    override fun createFragment(position: Int): Fragment {
       return CardItemDataFragment.newInstance(saveCard,position)
    }
}