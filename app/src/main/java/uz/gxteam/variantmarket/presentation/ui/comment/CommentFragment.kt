package uz.gxteam.variantmarket.presentation.ui.comment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.gxteam.variantmarket.R
import uz.gxteam.variantmarket.databinding.FragmentCardBinding
import uz.gxteam.variantmarket.databinding.FragmentCommentBinding
import uz.gxteam.variantmarket.presentation.ui.base.BaseFragment

class CommentFragment : BaseFragment<FragmentCommentBinding>() {
    override fun setup(savedInstanceState: Bundle?) {
        binding.apply {
            backBtn.setOnClickListener {
                appCompositionRoot.screenNavigate.popBackStack()
            }
        }
    }

    override fun start(savedInstanceState: Bundle?) {

    }

    override fun inflateViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentCommentBinding =
        FragmentCommentBinding.inflate(inflater,container,false)
}