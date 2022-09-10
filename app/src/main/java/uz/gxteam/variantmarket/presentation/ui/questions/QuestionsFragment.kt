package uz.gxteam.variantmarket.presentation.ui.questions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.gxteam.variantmarket.R
import uz.gxteam.variantmarket.databinding.FragmentProductDataBinding
import uz.gxteam.variantmarket.databinding.FragmentQuestionsBinding
import uz.gxteam.variantmarket.presentation.ui.base.BaseFragment


class QuestionsFragment : BaseFragment<FragmentQuestionsBinding>() {
    override fun setup(savedInstanceState: Bundle?) {
        menuIconHome()
        binding.apply {

        }
    }

    override fun start(savedInstanceState: Bundle?) {

    }

    override fun inflateViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentQuestionsBinding =
        FragmentQuestionsBinding.inflate(inflater,container,false)


}