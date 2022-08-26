package uz.gxteam.variantmarket.presentation.ui.cardPage

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import uz.gxteam.variantmarket.R
import uz.gxteam.variantmarket.adapters.genericAdapter.AdapterGeneric
import uz.gxteam.variantmarket.databinding.FragmentCardBinding
import uz.gxteam.variantmarket.models.cardData.plasticCard.BankCard
import uz.gxteam.variantmarket.models.history.History
import uz.gxteam.variantmarket.presentation.ui.base.BaseFragment
import java.lang.Math.abs


class CardFragment : BaseFragment<FragmentCardBinding>() {
    private lateinit var adapterGeneric:AdapterGeneric<BankCard>
    private lateinit var adapterHistory:AdapterGeneric<History>
    override fun setup(savedInstanceState: Bundle?) {
        binding.apply {
            createCardHolder()
            cardBank.setOnClickListener {
                appCompositionRoot.screenNavigate.createSaveCardUser()
            }

            addCardSecond.setOnClickListener {
                appCompositionRoot.screenNavigate.createSaveCardUser()
            }
           adapterHistory = AdapterGeneric(R.layout.item_money_history,historyAdapter()){ history, position ->

           }
            rvHistory.adapter = adapterHistory
        }
    }

    override fun start(savedInstanceState: Bundle?) {

    }


    fun getBankCardList():List<BankCard>{
        var listBankCard = ArrayList<BankCard>()
        listBankCard.add(BankCard("Doston Eshmurodov","9860170105741160","04/27","Humo","Ipak yo'li bank"))
        listBankCard.add(BankCard("Doston Eshmurodov","9860170105741160","04/27","Humo","Ipak yo'li bank"))
        listBankCard.add(BankCard("Doston Eshmurodov","9860170105741160","04/27","Humo","Ipak yo'li bank"))
        return listBankCard
    }

    private fun createCardHolder() {
       binding.apply {
           adapterGeneric = AdapterGeneric(R.layout.bank_card,getBankCardList() as ArrayList<BankCard> ){ data, position->}
           viewPager2.clipChildren = false
           viewPager2.clipToPadding = false
           viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL
           viewPager2.adapter = adapterGeneric
           springDotsIndicator.attachTo(viewPager2)
           viewPager2.offscreenPageLimit = 1

           val nextItemVisibleWidth = resources.getDimension(R.dimen.next_item_visible_width)
           val currentItemMargin =
               resources.getDimension(R.dimen.viewpager_horizontal_margin)
           val pageTranslation = nextItemVisibleWidth + currentItemMargin
           viewPager2.setPageTransformer { page: View, position: Float ->
               page.translationX = -pageTranslation * position
               page.scaleY = 1 - (0.25f * kotlin.math.abs(position))
               page.alpha = 0.25f + (1 - kotlin.math.abs(position))
           }
           val itemDecoration = PagerMarginItemDecoration(
               requireContext(),
               R.dimen.viewpager_horizontal_margin
           )
           viewPager2.addItemDecoration(itemDecoration)
       }
    }


    class PagerMarginItemDecoration(context: Context, @DimenRes horizontalMargin: Int) :
        RecyclerView.ItemDecoration() {

        private val horizontalMarginInPx: Int =
            context.resources.getDimension(horizontalMargin).toInt()

        override fun getItemOffsets(
            outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
        ) {
            outRect.right = horizontalMarginInPx
            outRect.left = horizontalMarginInPx
        }
    }
}