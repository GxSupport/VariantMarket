package uz.gxteam.variantmarket.presentation.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import uz.gxteam.variantmarket.databinding.FragmentGalleryBinding
import uz.gxteam.variantmarket.databinding.FragmentTermsOfUseBinding
import uz.gxteam.variantmarket.presentation.ui.base.BaseFragment

class GalleryFragment : BaseFragment<FragmentGalleryBinding>(){
    override fun setup(savedInstanceState: Bundle?) {
        binding.apply {

        }
    }

    override fun start(savedInstanceState: Bundle?) {

    }

    override fun inflateViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentGalleryBinding  =
        FragmentGalleryBinding.inflate(inflater,container,false)
}