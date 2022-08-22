package uz.gxteam.variantmarket.presentation.ui.homeScreen.view.orders

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.gxteam.variantmarket.R
import uz.gxteam.variantmarket.adapters.genericAdapter.AdapterGeneric
import uz.gxteam.variantmarket.databinding.FragmentOrdersBinding
import uz.gxteam.variantmarket.models.orders.Orders
import uz.gxteam.variantmarket.presentation.ui.base.BaseFragment

class OrdersFragment : BaseFragment<FragmentOrdersBinding>() {
    private lateinit var adapterGeneric: AdapterGeneric<Orders>
    override fun setup(savedInstanceState: Bundle?) {
        binding.apply {

        }
    }

    override fun start(savedInstanceState: Bundle?) {

    }
}