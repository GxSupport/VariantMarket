package uz.gxteam.variantmarket.presentation.ui.create_card

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import uz.gxteam.variantmarket.R
import uz.gxteam.variantmarket.databinding.FragmentCardItemDataBinding
import uz.gxteam.variantmarket.models.cardData.saveCard.SaveCard
import uz.gxteam.variantmarket.presentation.ui.base.BaseFragment
import uz.gxteam.variantmarket.utils.extensions.textApp


private const val ARG_PARAM1 = "saveCard"
private const val ARG_PARAM2 = "position"
@AndroidEntryPoint
class CardItemDataFragment : BaseFragment<FragmentCardItemDataBinding>() {
    private var saveCard: SaveCard? = null
    private var position: Int? = null
    companion object {
       @JvmStatic
        fun newInstance(saveCard: SaveCard,position:Int) =
            CardItemDataFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, saveCard)
                    putSerializable(ARG_PARAM2, position)
                }
            }
    }

    override fun setup(savedInstanceState: Bundle?) {
        binding.apply {
            appCompositionRoot.mainViewModelApp.dataLive.observe(appCompositionRoot.activityApp){ saveCard->
                if (saveCard!=null){
                    cardNumber.textApp(saveCard.numberCard)
                    date.textApp(saveCard.date)
                    nameCardUser.textApp(saveCard.name)
                    bankName.textApp(saveCard.bank)
                    if (saveCard.cardType==0) cardType.setImageResource(R.drawable.ic_humo)
                    else cardType.setImageResource(R.drawable.ic_uzcard)
                }
            }

            when(position){
                0->{
                    backImage.setImageResource(R.drawable.background_card)
                }
                1->{
                    backImage.setImageResource(R.drawable.background_card2)
                }
                2->{
                    backImage.setImageResource(R.drawable.background_card3)
                }
                3->{
                    backImage.setImageResource(R.drawable.background_card4)
                }
            }
        }
    }

    override fun start(savedInstanceState: Bundle?) {
        arguments.let {
          saveCard = it?.getSerializable(ARG_PARAM1) as SaveCard
          position = it.getInt(ARG_PARAM2,0)
        }
    }
}