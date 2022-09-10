package uz.gxteam.variantmarket.presentation.ui.dataProduct

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import uz.gxteam.variantmarket.R
import uz.gxteam.variantmarket.adapters.genericAdapter.AdapterGeneric
import uz.gxteam.variantmarket.adapters.infoProduct.InfoProductAdapter
import uz.gxteam.variantmarket.databinding.FragmentAuthBinding
import uz.gxteam.variantmarket.databinding.FragmentDataProductBinding
import uz.gxteam.variantmarket.models.local.sliderData.Filial
import uz.gxteam.variantmarket.models.local.sliderData.InfoData
import uz.gxteam.variantmarket.models.local.sliderData.InfoProduct
import uz.gxteam.variantmarket.models.local.sliderData.SlideData
import uz.gxteam.variantmarket.presentation.ui.base.BaseFragment
import uz.gxteam.variantmarket.utils.extensions.textApp


class DataProductFragment : BaseFragment<FragmentDataProductBinding>() {
    private lateinit var adapterGeneric: AdapterGeneric<Int>
    private lateinit var adapterGenericSize: AdapterGeneric<String>
    private lateinit var  adapterGenericCate: AdapterGeneric<Int>
    private lateinit var  adapterGenericFilial: AdapterGeneric<Filial>
    private lateinit var  adapterGenericSizeProduct: AdapterGeneric<String>
    private lateinit var infoProductAdapter:InfoProductAdapter
    private var hasMotionScrolled = false
    override fun start(savedInstanceState: Bundle?) {

    }

    override fun setup(savedInstanceState: Bundle?) {
     binding.apply {
         adapterGeneric = AdapterGeneric(R.layout.item_image,dataSave().image as java.util.ArrayList<Int>){data,position->}
         viewPager2.adapter = adapterGeneric
         springDotsIndicator.attachTo(viewPager2)
         var clickCount = 0
         lickBtn.setOnClickListener {
             if (clickCount==0){
                 lickIcon.setImageResource(R.drawable.ic_like_1)
                 clickCount++
             }else if (clickCount==1){
                 lickIcon.setImageResource(R.drawable.ic_like)
                 clickCount = 0
             }
         }
         bacBtn.setOnClickListener {
             appCompositionRoot.screenNavigate.popBackStack()
         }

         title.textApp(dataSave().message)
         summa.textApp(dataSave().summa)

         shareBtn.setOnClickListener {
             appCompositionRoot.shareData()
         }
         adapterGenericCate = AdapterGeneric(R.layout.item_rv_product_image,dataSave().imageType as ArrayList<Int>){data,position->
             adapterGenericCate.setPositionClick(position)
             when(data){
                 R.drawable.a53->{
                     adapterGeneric.currentResult = listPhone53()
                     adapterGeneric.notifyDataSetChanged()
                 }
                 R.drawable.a53_white->{
                     adapterGeneric.currentResult = listPhone53White()
                     adapterGeneric.notifyDataSetChanged()
                 }
                 R.drawable.a53_black->{
                     adapterGeneric.currentResult = listPhone53Black()
                     adapterGeneric.notifyDataSetChanged()
                 }

             }
         }
         rvImage.adapter = adapterGenericCate

         adapterGenericSize = AdapterGeneric(R.layout.item_check,dataSave().sizeList as ArrayList<String>){data,position->
             adapterGenericSize.setPositionClick(position)
         }
         rvCheck.adapter = adapterGenericSize

         adapterGenericSizeProduct = AdapterGeneric(R.layout.item_check_size,dataSave().sizeProduct as ArrayList<String>){data,position->
             adapterGenericSizeProduct.setPositionClick(position)
         }
         rvSize.adapter = adapterGenericSizeProduct
         /**Info Product adapter**/
         infoProductAdapter = InfoProductAdapter(dataSave().productInfo)
         rvCharacteristics.adapter = infoProductAdapter
         //Filial adapter
         adapterGenericFilial = AdapterGeneric(R.layout.item_other_product,dataSave().filialList as ArrayList<Filial>){filial, position ->

         }
         rvOther.adapter = adapterGenericFilial
         // TODO: ViewMore Info Btn click
         viewMoreInfo.setOnClickListener{
             appCompositionRoot.screenNavigate.createProdInfo()
         }
         // TODO: Coment
         comment.setOnClickListener {
             appCompositionRoot.screenNavigate.createCommentView()
         }

     }
    }





    fun listPhone53():ArrayList<Int>{
        var listImage:ArrayList<Int> = ArrayList()
        listImage.add(R.drawable.a53)
        listImage.add(R.drawable.a53)
        listImage.add(R.drawable.a53)
        listImage.add(R.drawable.a53)
        listImage.add(R.drawable.a53)
        listImage.add(R.drawable.a53)
        return listImage
    }
    fun listPhone53White():ArrayList<Int>{
        var listImage:ArrayList<Int> = ArrayList()
        listImage.add(R.drawable.a53_white)
        listImage.add(R.drawable.a53_white)
        listImage.add(R.drawable.a53_white)
        listImage.add(R.drawable.a53_white)
        listImage.add(R.drawable.a53_white)
        listImage.add(R.drawable.a53_white)
        return listImage
    }
    fun listPhone53Black():ArrayList<Int>{
        var listImage:ArrayList<Int> = ArrayList()
        listImage.add(R.drawable.a53_black)
        listImage.add(R.drawable.a53_black)
        listImage.add(R.drawable.a53_black)
        listImage.add(R.drawable.a53_black)
        listImage.add(R.drawable.a53_black)
        listImage.add(R.drawable.a53_black)
        return listImage
    }

    fun dataSave():SlideData{
        var listImage:ArrayList<Int> = ArrayList()
        var listSize:ArrayList<String> = ArrayList()
        listImage.add(R.drawable.a53)
        listImage.add(R.drawable.a53)
        listImage.add(R.drawable.a53)
        listImage.add(R.drawable.a53)
        listImage.add(R.drawable.a53)
        listImage.add(R.drawable.a53)
        // bo'lib  to'lash month
        listSize.add("3 oyga")
        listSize.add("6 oyga")
        listSize.add("9 oyga")
        listSize.add("12 oyga")
        listSize.add("16 oyga")
        listSize.add("18 oyga")
        listSize.add("24 oyga")
        //imagetype
        var listImageType = ArrayList<Int>()
        listImageType.add(R.drawable.a53)
        listImageType.add(R.drawable.a53_black)
        listImageType.add(R.drawable.a53_white)
        //sizeProduct phone
        var listImageSize = ArrayList<String>()
        listImageSize.add("6/128")
        listImageSize.add("6/256")
        listImageSize.add("8/128")
        // info product
        var productData = ArrayList<InfoData>()
        var listProduct = ArrayList<InfoProduct>()
        //Xususiyatlari
        listProduct.add(InfoProduct("Kafolat","1 oy"))
        listProduct.add(InfoProduct("Vazni","202 g"))
        listProduct.add(InfoProduct("Operatsion sistema versiyasi","Android 11"))
        listProduct.add(InfoProduct("Rangi","Silver"))
        listProduct.add(InfoProduct("Batareykasi","5100 mA"))
        productData.add(InfoData("Umumiy ma'lumot",listProduct))
        //Aloqa
        listProduct = ArrayList()
        listProduct.add(InfoProduct("Wifi","Est"))
        listProduct.add(InfoProduct("Bulutoh","Mavjud"))
        listProduct.add(InfoProduct("NFC","Mavjud"))
        listProduct.add(InfoProduct("Infraqizil port","Bor"))
        productData.add(InfoData("Aloqa",listProduct))
        //Aloqa
        listProduct = ArrayList()
        listProduct.add(InfoProduct("Display turi","AMOLED"))
        listProduct.add(InfoProduct("Ekran o'lchami","2400x1080"))
        listProduct.add(InfoProduct("Diagonal","6.67"))
        productData.add(InfoData("Smartfon displayi",listProduct))
        //list filial
        var listFilial = ArrayList<Filial>()
        listFilial = ArrayList()
        listFilial.add(Filial(true,"JOYBOX","125 667 so'm","x 18 oyga","1 465 000"))
        listFilial.add(Filial(false,"HUMO CREDIT","126 967 so'm","x 18 oyga","1 465 000"))
        listFilial.add(Filial(false,"Saturn","132 427 so'm","x 18 oyga","1 528 000"))
        listFilial.add(Filial(false,"TELTORG","134 333 so'm","x 18 oyga","1 550 000"))
        return SlideData(listImage,false,"Blah",
            "A 53 ","Samsung A53 Telefoni",
            "3.7","183 Reviews","3 450 000 so'm" ,"$123",
            "15","White","6",listImage,listSize,"",listImageType,"346 000 so'm dan / 12 oyga Muddatli to'lov",
        listImageSize,productData,listFilial)

    }

    override fun onResume() {
        super.onResume()
        if (hasMotionScrolled) binding.motion.progress = MOTION_TRANSITION_COMPLETED
    }

    override fun onPause() {
        super.onPause()
        hasMotionScrolled = binding.motion.progress > MOTION_TRANSITION_INITIAL
    }

    companion object {
        private const val MOTION_TRANSITION_COMPLETED = 1F
        private const val MOTION_TRANSITION_INITIAL = 0F
    }

    override fun inflateViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentDataProductBinding =
        FragmentDataProductBinding.inflate(inflater,container,false)
}