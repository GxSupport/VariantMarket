package uz.gxteam.variantmarket.presentation.ui.productInfo


import android.os.Bundle
import uz.gxteam.variantmarket.adapters.infoProduct.InfoProductAdapter
import uz.gxteam.variantmarket.databinding.FragmentProductInfoBinding
import uz.gxteam.variantmarket.models.local.sliderData.InfoData
import uz.gxteam.variantmarket.models.local.sliderData.InfoProduct
import uz.gxteam.variantmarket.presentation.ui.base.BaseFragment


class ProductInfoFragment : BaseFragment<FragmentProductInfoBinding>() {
    private lateinit var infoProductAdapter: InfoProductAdapter
    override fun start(savedInstanceState: Bundle?) {

    }
    override fun setup(savedInstanceState: Bundle?) {
        binding.apply {
            infoProductAdapter = InfoProductAdapter(getDataInfo())
            rvInfo.adapter = infoProductAdapter
            back.setOnClickListener {
                appCompositionRoot.screenNavigate.popBackStack()
            }
        }
    }


    fun getDataInfo():List<InfoData>{
        var listInfo  = ArrayList<InfoData>()
        var listProductInfo = ArrayList<InfoProduct>()
        listProductInfo.add(InfoProduct("Tip","Smartfon"))
        listProductInfo.add(InfoProduct("Operatsion sistema versiyasi","Android 11"))
        listProductInfo.add(InfoProduct("Simkarta joyi","2 ta SIM"))
        listProductInfo.add(InfoProduct("Vazni","202 g"))
        listProductInfo.add(InfoProduct("O'lchami","164.9 x 77 x 9 mm"))
        listInfo.add(InfoData("Umumiy malumotlar",listProductInfo))
        listProductInfo = ArrayList()
        listProductInfo.add(InfoProduct("Tip","Smartfon"))
        listProductInfo.add(InfoProduct("Operatsion sistema versiyasi","Android 11"))
        listProductInfo.add(InfoProduct("Simkarta joyi","2 ta SIM"))
        listProductInfo.add(InfoProduct("Vazni","202 g"))
        listProductInfo.add(InfoProduct("O'lchami","164.9 x 77 x 9 mm"))
        listInfo.add(InfoData("Ekran",listProductInfo))
        listProductInfo = ArrayList()
        listProductInfo.add(InfoProduct("Tip","Smartfon"))
        listProductInfo.add(InfoProduct("Operatsion sistema versiyasi","Android 11"))
        listProductInfo.add(InfoProduct("Simkarta joyi","2 ta SIM"))
        listProductInfo.add(InfoProduct("Vazni","202 g"))
        listProductInfo.add(InfoProduct("O'lchami","164.9 x 77 x 9 mm"))
        listInfo.add(InfoData("Ekran",listProductInfo))
        listProductInfo = ArrayList()
        listProductInfo.add(InfoProduct("Tip","Smartfon"))
        listProductInfo.add(InfoProduct("Operatsion sistema versiyasi","Android 11"))
        listProductInfo.add(InfoProduct("Simkarta joyi","2 ta SIM"))
        listProductInfo.add(InfoProduct("Vazni","202 g"))
        listProductInfo.add(InfoProduct("O'lchami","164.9 x 77 x 9 mm"))
        listInfo.add(InfoData("Ekran",listProductInfo))
        listProductInfo = ArrayList()
        listProductInfo.add(InfoProduct("Tip","Smartfon"))
        listProductInfo.add(InfoProduct("Operatsion sistema versiyasi","Android 11"))
        listProductInfo.add(InfoProduct("Simkarta joyi","2 ta SIM"))
        listProductInfo.add(InfoProduct("Vazni","202 g"))
        listProductInfo.add(InfoProduct("O'lchami","164.9 x 77 x 9 mm"))
        listInfo.add(InfoData("Ekran",listProductInfo))
        listProductInfo = ArrayList()
        listProductInfo.add(InfoProduct("Tip","Smartfon"))
        listProductInfo.add(InfoProduct("Operatsion sistema versiyasi","Android 11"))
        listProductInfo.add(InfoProduct("Simkarta joyi","2 ta SIM"))
        listProductInfo.add(InfoProduct("Vazni","202 g"))
        listProductInfo.add(InfoProduct("O'lchami","164.9 x 77 x 9 mm"))
        listInfo.add(InfoData("Ekran",listProductInfo))

        return listInfo
    }


}