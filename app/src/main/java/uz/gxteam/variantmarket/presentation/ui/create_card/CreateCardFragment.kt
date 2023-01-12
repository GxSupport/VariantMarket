package uz.gxteam.variantmarket.presentation.ui.create_card

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DimenRes
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import dagger.hilt.android.AndroidEntryPoint
import uz.gxteam.variantmarket.R
import uz.gxteam.variantmarket.adaptersLocale.viewPagerAdapter.CardAdapter
import uz.gxteam.variantmarket.databinding.FragmentCreateCardBinding
import uz.gxteam.variantmarket.models.local.cardData.saveCard.SaveCard
import uz.gxteam.variantmarket.presentation.ui.base.BaseFragment

@AndroidEntryPoint
class CreateCardFragment : BaseFragment<FragmentCreateCardBinding>() {
    private lateinit var cardAdapter: CardAdapter
    override fun setup(savedInstanceState: Bundle?) {
        binding.apply {
            removeMenuHome()

            cardNumber()
            // TODO: Default value card Data
            var saveCard = SaveCard("-----------------------","0000 0000 0000 0000","00/00",0,"-----------------")
            appCompositionRoot.mainViewModelApp.setValuePos(saveCard)
            createCardHolder(saveCard)
            cardNumber.addTextChangedListener {
                val str = cardNumber.rawText.toString().trim()
                if (str.isNotEmpty()){
                    if (str.length in 4..8){
                        saveCard.numberCard = "${cardNumber.rawText.toString().trim().substring(0,4)} ${cardNumber.rawText.toString().trim().substring(4)}"
                    }else if (str.length in 8..12){
                        saveCard.numberCard = "${cardNumber.rawText.toString().trim().substring(0,4)} ${cardNumber.rawText.toString().trim().substring(4,8)} ${cardNumber.rawText.toString().trim().substring(8)}"
                    }else if (str.length in 12..16){
                        saveCard.numberCard ="${cardNumber.rawText.toString().trim().substring(0,4)} ${cardNumber.rawText.toString().trim().substring(4,8)} ${cardNumber.rawText.toString().trim().substring(8,12)} ${cardNumber.rawText.toString().trim().substring(12)}"
                    }else {
                        saveCard.numberCard = cardNumber.rawText.toString().trim()
                    }
                }else{
                    saveCard.numberCard = "0000 0000 0000 0000"
                }
                appCompositionRoot.mainViewModelApp.setValuePos(saveCard)
            }
            date.addTextChangedListener {
                val date = date.rawText.toString().trim()
                if (date.isNotEmpty()){
                    if (date.length>=2) saveCard.date = "${date.substring(0,2)}/${date.substring(2)}"
                    else saveCard.date = date
                }else{
                    saveCard.date="00/00"
                }
                appCompositionRoot.mainViewModelApp.setValuePos(saveCard)
            }
            name.addTextChangedListener { text->
                if (text.toString().trim().isNotEmpty()){
                    saveCard.name = text.toString()
                }else {
                    saveCard.name = "-----------------------"
                }
                appCompositionRoot.mainViewModelApp.setValuePos(saveCard)
            }
            bankName.addTextChangedListener {text->
                if (text.toString().trim().isNotEmpty()){
                    saveCard.bank = text.toString()
                }else{
                    saveCard.bank = "-----------------"
                }
                appCompositionRoot.mainViewModelApp.setValuePos(saveCard)
            }

            appCompositionRoot.mainViewModelApp.dataLive.observe(appCompositionRoot.lifsycleOwner){ saveCard->
                if (saveCard!=null){
                    if (saveCard.cardType==0){
                        humo.setChipBackgroundColorResource(R.color.app_background)
                        humo.setChipStrokeColorResource(R.color.strocke_color)
                        humo.setTextColor(ContextCompat.getColor(requireContext(),R.color.white))

                        uzcard.setChipBackgroundColorResource(R.color.application_background)
                        uzcard.setChipStrokeColorResource(R.color.app_background)
                        uzcard.setTextColor(ContextCompat.getColor(requireContext(),R.color.text_color_app))
                    }else{
                        humo.setChipBackgroundColorResource(R.color.application_background)
                        humo.setChipStrokeColorResource(R.color.app_background)
                        humo.setTextColor(ContextCompat.getColor(requireContext(),R.color.text_color_app))

                        uzcard.setChipBackgroundColorResource(R.color.app_background)
                        uzcard.setChipStrokeColorResource(R.color.strocke_color)
                        uzcard.setTextColor(ContextCompat.getColor(requireContext(),R.color.white))
                    }
                }
            }

            humo.setOnClickListener {
                saveCard.cardType = 0
                appCompositionRoot.mainViewModelApp.setValuePos(saveCard)
            }

            uzcard.setOnClickListener {
                saveCard.cardType = 1
                appCompositionRoot.mainViewModelApp.setValuePos(saveCard)
            }
            humo.layoutDirection = View.LAYOUT_DIRECTION_LOCALE
            uzcard.layoutDirection = View.LAYOUT_DIRECTION_LOCALE
        }
    }


    override fun start(savedInstanceState: Bundle?) {

    }

    fun cardNumber(){
        binding.cardNumber.doAfterTextChanged {
            if (binding.cardNumber.rawText.toString().trim().length==16){
                binding.date.requestFocus()
            }
        }
        binding.date.doAfterTextChanged {

            if(binding.date.rawText.toString().trim().length==4){
                binding.name.requestFocus()
            }
        }
    }




    private fun createCardHolder(saveCard: SaveCard) {
        binding.apply {

            cardAdapter = CardAdapter(requireActivity(),saveCard)
            viewPager.adapter = cardAdapter
            viewPager.clipChildren = false
            viewPager.clipToPadding = false
            viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

            viewPager.offscreenPageLimit = 1

            val nextItemVisibleWidth = resources.getDimension(R.dimen.next_item_visible_width)
            val currentItemMargin =
                resources.getDimension(R.dimen.viewpager_horizontal_margin)
            val pageTranslation = nextItemVisibleWidth + currentItemMargin
            viewPager.setPageTransformer { page: View, position: Float ->
                page.translationX = -pageTranslation * position
                page.scaleY = 1 - (0.25f * kotlin.math.abs(position))
                page.alpha = 0.25f + (1 - kotlin.math.abs(position))
            }
            val itemDecoration = PagerMarginItemDecoration(
                requireContext(),
                R.dimen.viewpager_horizontal_margin
            )
            viewPager.addItemDecoration(itemDecoration)
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

    override fun inflateViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentCreateCardBinding =
        FragmentCreateCardBinding.inflate(inflater,container,false)

}