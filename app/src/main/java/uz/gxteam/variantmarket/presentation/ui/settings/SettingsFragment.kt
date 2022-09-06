package uz.gxteam.variantmarket.presentation.ui.settings

import android.os.Bundle
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import uz.gxteam.variantmarket.R
import uz.gxteam.variantmarket.databinding.FragmentSettingsBinding
import uz.gxteam.variantmarket.presentation.ui.base.BaseFragment
import uz.gxteam.variantmarket.utils.AppConstant.EN
import uz.gxteam.variantmarket.utils.AppConstant.RU
import uz.gxteam.variantmarket.utils.AppConstant.UZB
import uz.gxteam.variantmarket.utils.extensions.textApp
import uz.gxteam.variantmarket.utils.language.LocaleManager
import uz.gxteam.variantmarket.utils.sharedPreferences.MySharedPreferences
import uz.gxteam.variantmarket.viewModels.mainViewModel.MainViewModel

@AndroidEntryPoint
class SettingsFragment : BaseFragment<FragmentSettingsBinding>() {
    private val mainViewModel:MainViewModel by viewModels()
 override fun setup(savedInstanceState: Bundle?) {
        binding.apply {
            linearUserInfo.setOnClickListener {
                bottomSheetDialog(R.layout.user_info_dialog,mainViewModel.myShared)
            }
            language.setOnClickListener {
              bottomSheetDialog(R.layout.language_dialog,mainViewModel.myShared)
            }

            val persistedLocale = LocaleManager.getLanguage(requireContext())
            when(persistedLocale?.lowercase()){
                UZB.lowercase()->{
                    tvLangText(requireActivity().getString(R.string.uz))
                }
                RU.lowercase()->{
                    tvLangText(requireActivity().getString(R.string.ru))
                }
                EN.lowercase()->{
                    tvLangText(requireActivity().getString(R.string.cr))
                }
            }

        }
    }

    override fun start(savedInstanceState: Bundle?) {

    }

    fun tvLangText(str:String){
        binding.langTv.textApp(str)
    }


    fun bottomSheetDialog(layoutRes:Int,mySharedPreferences: MySharedPreferences){
        appCompositionRoot.bottomSheetDialogData(layoutRes,mySharedPreferences)
    }

}