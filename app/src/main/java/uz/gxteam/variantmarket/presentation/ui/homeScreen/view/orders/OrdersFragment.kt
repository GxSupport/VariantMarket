package uz.gxteam.variantmarket.presentation.ui.homeScreen.view.orders

import android.os.Bundle
import uz.gxteam.variantmarket.R
import uz.gxteam.variantmarket.adapters.genericAdapter.AdapterGeneric
import uz.gxteam.variantmarket.databinding.FragmentOrdersBinding
import uz.gxteam.variantmarket.models.local.orders.Orders
import uz.gxteam.variantmarket.presentation.ui.base.BaseFragment

class OrdersFragment : BaseFragment<FragmentOrdersBinding>() {
    private lateinit var adapterGeneric: AdapterGeneric<Orders>
    override fun setup(savedInstanceState: Bundle?) {
        binding.apply {
            adapterGeneric = AdapterGeneric(R.layout.order_item,getOrders()){orders, position ->

            }
            rv.adapter = adapterGeneric
        }
    }

    override fun start(savedInstanceState: Bundle?) {

    }
}