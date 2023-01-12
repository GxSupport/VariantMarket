package uz.gxteam.variantmarket.presentation.ui.homeScreen.view.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import uz.gxteam.variantmarket.R
import uz.gxteam.variantmarket.adaptersLocale.genericAdapter.AdapterGeneric
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

    override fun inflateViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentOrdersBinding =
        FragmentOrdersBinding.inflate(inflater,container,false)
}