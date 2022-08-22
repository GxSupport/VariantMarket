package uz.gxteam.variantmarket.presentation.ui.cateGoryData

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.gxteam.variantmarket.R
import uz.gxteam.variantmarket.databinding.FragmentCategoryBinding
import uz.gxteam.variantmarket.presentation.ui.base.BaseFragment

class CategoryDataFragment : BaseFragment<FragmentCategoryBinding>() {
    private var name:String?=null
    private var pos:Int?=null
    override fun setup(savedInstanceState: Bundle?) {
        arguments.apply {
           name = this?.getString("name")
           pos = this?.getInt("position",0)
        }
    }

    override fun start(savedInstanceState: Bundle?) {

    }
}