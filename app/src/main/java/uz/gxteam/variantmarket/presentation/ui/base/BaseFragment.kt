package uz.gxteam.variantmarket.presentation.ui.base

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import uz.gxteam.variantmarket.R
import uz.gxteam.variantmarket.models.local.allM.AllData
import uz.gxteam.variantmarket.models.local.allM.CategoryAll
import uz.gxteam.variantmarket.models.local.discount.Discount
import uz.gxteam.variantmarket.models.local.filter.FilterData
import uz.gxteam.variantmarket.models.local.history.History
import uz.gxteam.variantmarket.models.local.newsData.NewsData
import uz.gxteam.variantmarket.models.local.orders.Orders
import uz.gxteam.variantmarket.models.local.searchData.SearchDataAll
import uz.gxteam.variantmarket.models.local.simpleSlide.SlideData
import uz.gxteam.variantmarket.presentation.activitys.AuthActivity
import uz.gxteam.variantmarket.presentation.activitys.MainActivity
import java.lang.reflect.ParameterizedType
import kotlin.coroutines.CoroutineContext

abstract class BaseFragment<VB:ViewBinding>:Fragment(),CoroutineScope {

    private var _binding : VB? = null
    val binding :VB get() = _binding!!
    val appCompositionRoot get() = (activity as MainActivity).appCompositionRoot
    val appCompositionRootAuth get() = (activity as AuthActivity).appCompositionRoot
    var clickPosition = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        start(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val type = javaClass.genericSuperclass
        val clazz = (type as ParameterizedType).actualTypeArguments[0] as Class<VB>
        val method = clazz.getMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java)
        _binding = method.invoke(null, layoutInflater, container, false) as VB
        return requireNotNull(_binding!!.root)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup(savedInstanceState)
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main.immediate

    abstract fun setup(savedInstanceState: Bundle?)
    abstract fun start(savedInstanceState: Bundle?)

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }


    fun removeMenuHome(){
        (activity as MainActivity).binding.appBarMain.toolbarApp.menu.removeItem(R.id.nav_home)
        (activity as MainActivity).binding.appBarMain.toolbarApp.menu.removeItem(R.id.nav_gallery)
    }



    fun menuIconHome(){
        (activity as MainActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_main_menu)
    }


    fun toolbarLeftIcon(){
        appCompositionRoot.activityApp.supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_left)
    }

    fun toolbarShow(){
        (activity as MainActivity).supportActionBar?.show()
    }

    private lateinit var slideList:ArrayList<SlideData>
    private lateinit var discountList:ArrayList<Discount>
    private lateinit var allMainItemList:ArrayList<CategoryAll>
    private lateinit var listSearch:ArrayList<String>
    private lateinit var allMainItemListChild:ArrayList<AllData>
    private lateinit var allDataSearch:ArrayList<SearchDataAll>
    var count = 0
    var handler = Handler(Looper.getMainLooper())



    fun getOrders():ArrayList<Orders>{
        var ordersList = ArrayList<Orders>()
        ordersList.add(Orders("12.07.2022","12 oyga","Sansung A 53","Sansung A 53","1 350 000",0,1,"https://www.creditasia.uz/upload/iblock/eeb/1dy3y0jxsuym5c245uzo0nv27o9v20i7/smartfon-samsung-galaxy-a53-5g-sm-a536e-ds-128gb-light-blue.png"))
        ordersList.add(Orders("12.07.2022","12 oyga","Sansung A 53","Sansung A 53","1 350 000",1,1,"https://www.creditasia.uz/upload/iblock/eeb/1dy3y0jxsuym5c245uzo0nv27o9v20i7/smartfon-samsung-galaxy-a53-5g-sm-a536e-ds-128gb-light-blue.png"))
        ordersList.add(Orders("12.07.2022","12 oyga","Sansung A 53","Sansung A 53","1 350 000",0,1,"https://www.creditasia.uz/upload/iblock/eeb/1dy3y0jxsuym5c245uzo0nv27o9v20i7/smartfon-samsung-galaxy-a53-5g-sm-a536e-ds-128gb-light-blue.png"))
        ordersList.add(Orders("12.07.2022","12 oyga","Sansung A 53","Sansung A 53","1 350 000",1,1,"https://www.creditasia.uz/upload/iblock/eeb/1dy3y0jxsuym5c245uzo0nv27o9v20i7/smartfon-samsung-galaxy-a53-5g-sm-a536e-ds-128gb-light-blue.png"))
        ordersList.add(Orders("12.07.2022","12 oyga","Sansung A 53","Sansung A 53","1 350 000",0,1,"https://www.creditasia.uz/upload/iblock/eeb/1dy3y0jxsuym5c245uzo0nv27o9v20i7/smartfon-samsung-galaxy-a53-5g-sm-a536e-ds-128gb-light-blue.png"))
        ordersList.add(Orders("12.07.2022","12 oyga","Sansung A 53","Sansung A 53","1 350 000",1,1,"https://www.creditasia.uz/upload/iblock/eeb/1dy3y0jxsuym5c245uzo0nv27o9v20i7/smartfon-samsung-galaxy-a53-5g-sm-a536e-ds-128gb-light-blue.png"))
        ordersList.add(Orders("12.07.2022","12 oyga","Sansung A 53","Sansung A 53","1 350 000",0,1,"https://www.creditasia.uz/upload/iblock/eeb/1dy3y0jxsuym5c245uzo0nv27o9v20i7/smartfon-samsung-galaxy-a53-5g-sm-a536e-ds-128gb-light-blue.png"))
        ordersList.add(Orders("12.07.2022","12 oyga","Sansung A 53","Sansung A 53","1 350 000",1,1,"https://www.creditasia.uz/upload/iblock/eeb/1dy3y0jxsuym5c245uzo0nv27o9v20i7/smartfon-samsung-galaxy-a53-5g-sm-a536e-ds-128gb-light-blue.png"))
        return ordersList
    }




    fun getNewsData():ArrayList<NewsData>{
        var listNews = ArrayList<NewsData>()
        listNews.add(
            NewsData("Samsung A 53","https://www.creditasia.uz/upload/iblock/eeb/1dy3y0jxsuym5c245uzo0nv27o9v20i7/smartfon-samsung-galaxy-a53-5g-sm-a536e-ds-128gb-light-blue.png","Samsung A 53 bu telefon juda zur",
            "Blah Blah Blah Blah Blah Blah",false,15000.0,false,0.0,12)
        )
        listNews.add(
            NewsData("Samsung A 53","https://store.storeimages.cdn-apple.com/4668/as-images.apple.com/is/MKUQ3_VW_PF+watch-44-alum-spacegray-nc-se_VW_PF_WF_CO_GEO_IN?wid=1400&hei=1400&trim=1,0&fmt=p-jpg&qlt=95&.v=1632171068000,1630708998000","Samsung A 53 bu telefon juda zur",
            "Blah Blah Blah Blah Blah Blah",true,40000.0,false,0.0,18)
        )
        listNews.add(
            NewsData("Samsung A 53","https://www.creditasia.uz/upload/iblock/eeb/1dy3y0jxsuym5c245uzo0nv27o9v20i7/smartfon-samsung-galaxy-a53-5g-sm-a536e-ds-128gb-light-blue.png","Samsung A 53 bu telefon juda zur",
            "Blah Blah Blah Blah Blah Blah",true,30000.0,true,15.0,6)
        )
        listNews.add(
            NewsData("Samsung A 53","https://store.storeimages.cdn-apple.com/4668/as-images.apple.com/is/MKUQ3_VW_PF+watch-44-alum-spacegray-nc-se_VW_PF_WF_CO_GEO_IN?wid=1400&hei=1400&trim=1,0&fmt=p-jpg&qlt=95&.v=1632171068000,1630708998000","Samsung A 53 bu telefon juda zur",
            "Blah Blah Blah Blah Blah Blah",true,25000.0,true,10.0,9)
        )
        listNews.add(
            NewsData("Samsung A 53","https://www.creditasia.uz/upload/iblock/eeb/1dy3y0jxsuym5c245uzo0nv27o9v20i7/smartfon-samsung-galaxy-a53-5g-sm-a536e-ds-128gb-light-blue.png","Samsung A 53 bu telefon juda zur",
            "Blah Blah Blah Blah Blah Blah",false,15000.0,false,0.0,12)
        )
        listNews.add(
            NewsData("Samsung A 53","https://store.storeimages.cdn-apple.com/4668/as-images.apple.com/is/MKUQ3_VW_PF+watch-44-alum-spacegray-nc-se_VW_PF_WF_CO_GEO_IN?wid=1400&hei=1400&trim=1,0&fmt=p-jpg&qlt=95&.v=1632171068000,1630708998000","Samsung A 53 bu telefon juda zur",
            "Blah Blah Blah Blah Blah Blah",true,40000.0,false,0.0,24)
        )
        listNews.add(
            NewsData("Samsung A 53","https://www.creditasia.uz/upload/iblock/eeb/1dy3y0jxsuym5c245uzo0nv27o9v20i7/smartfon-samsung-galaxy-a53-5g-sm-a536e-ds-128gb-light-blue.png","Samsung A 53 bu telefon juda zur",
            "Blah Blah Blah Blah Blah Blah",true,30000.0,true,15.0,12)
        )
        listNews.add(
            NewsData("Samsung A 53","https://store.storeimages.cdn-apple.com/4668/as-images.apple.com/is/MKUQ3_VW_PF+watch-44-alum-spacegray-nc-se_VW_PF_WF_CO_GEO_IN?wid=1400&hei=1400&trim=1,0&fmt=p-jpg&qlt=95&.v=1632171068000,1630708998000","Samsung A 53 bu telefon juda zur",
            "Blah Blah Blah Blah Blah Blah",true,25000.0,true,10.0,18)
        )
        return listNews
    }


    fun loadAdvertising():ArrayList<SlideData>{
        slideList = ArrayList()
        slideList.add(SlideData(image = "https://www.creditasia.uz/upload/iblock/eeb/1dy3y0jxsuym5c245uzo0nv27o9v20i7/smartfon-samsung-galaxy-a53-5g-sm-a536e-ds-128gb-light-blue.png","Dostonbek"))
        slideList.add(SlideData(image ="https://store.storeimages.cdn-apple.com/4668/as-images.apple.com/is/MKUQ3_VW_PF+watch-44-alum-spacegray-nc-se_VW_PF_WF_CO_GEO_IN?wid=1400&hei=1400&trim=1,0&fmt=p-jpg&qlt=95&.v=1632171068000,1630708998000","Dostonbek"))
        slideList.add(SlideData(image = "https://avatars.mds.yandex.net/i?id=6538edc19a05cea83f86c07fff26b135-5238639-images-thumbs&n=13","Dostonbek"))
        slideList.add(SlideData(image = "https://avatars.mds.yandex.net/i?id=788c8e4e621525ed9762fa1adb76e78c-7069324-images-thumbs&n=13","Dostonbek"))
        slideList.add(SlideData(image = "https://avatars.mds.yandex.net/i?id=6538edc19a05cea83f86c07fff26b135-5238639-images-thumbs&n=13","Dostonbek"))
        slideList.add(SlideData(image = "https://avatars.mds.yandex.net/i?id=788c8e4e621525ed9762fa1adb76e78c-7069324-images-thumbs&n=13","Dostonbek"))
        return slideList
    }


    fun loadDiscount():ArrayList<Discount>{
        discountList = ArrayList()
        discountList.add(Discount(title = "Samsung A 53", persent = "Upto 40% OFF","https://www.creditasia.uz/upload/iblock/eeb/1dy3y0jxsuym5c245uzo0nv27o9v20i7/smartfon-samsung-galaxy-a53-5g-sm-a536e-ds-128gb-light-blue.png"))
        discountList.add(Discount(title = "I Watch", persent = "Upto 40% OFF","https://store.storeimages.cdn-apple.com/4668/as-images.apple.com/is/MKUQ3_VW_PF+watch-44-alum-spacegray-nc-se_VW_PF_WF_CO_GEO_IN?wid=1400&hei=1400&trim=1,0&fmt=p-jpg&qlt=95&.v=1632171068000,1630708998000"))
        discountList.add(Discount(title = "Samsung A 53", persent = "Upto 40% OFF","https://www.creditasia.uz/upload/iblock/eeb/1dy3y0jxsuym5c245uzo0nv27o9v20i7/smartfon-samsung-galaxy-a53-5g-sm-a536e-ds-128gb-light-blue.png"))
        discountList.add(Discount(title = "I Watch", persent = "Upto 40% OFF","https://store.storeimages.cdn-apple.com/4668/as-images.apple.com/is/MKUQ3_VW_PF+watch-44-alum-spacegray-nc-se_VW_PF_WF_CO_GEO_IN?wid=1400&hei=1400&trim=1,0&fmt=p-jpg&qlt=95&.v=1632171068000,1630708998000"))
        discountList.add(Discount(title = "Samsung A 53", persent = "Upto 40% OFF","https://www.creditasia.uz/upload/iblock/eeb/1dy3y0jxsuym5c245uzo0nv27o9v20i7/smartfon-samsung-galaxy-a53-5g-sm-a536e-ds-128gb-light-blue.png"))
        discountList.add(Discount(title = "I Watch", persent = "Upto 40% OFF","https://store.storeimages.cdn-apple.com/4668/as-images.apple.com/is/MKUQ3_VW_PF+watch-44-alum-spacegray-nc-se_VW_PF_WF_CO_GEO_IN?wid=1400&hei=1400&trim=1,0&fmt=p-jpg&qlt=95&.v=1632171068000,1630708998000"))
        return discountList
    }


    fun getMainListData():ArrayList<CategoryAll>{
        allMainItemListChild = ArrayList()
        allMainItemList = ArrayList()
        allMainItemListChild.add(AllData(image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTYplhXINf1ICpTjtnIHiX9k5YG45tzlDIosA&usqp=CAU","like","Top Seller","Assalomu Aleykum","$162","$180","10% ON","5,3","(580)"))
        allMainItemListChild.add(AllData(image = "https://images.samsung.com/za/smartphones/galaxy-s21/buy/s21_family_kv_mo_img.jpg?imwidth=720","like","Top Seller","Assalomu Aleykum","$162","$180","10% ON","5,3","(580)"))
        allMainItemListChild.add(AllData(image = "https://olcha.uz/image/340x340/products/GkMT0H5gZ2BXda3lUWFrn49EM8RuruogXV2fOqKP6jYdzk9Bx0jC3T3vTawa.jpeg","like","Top Seller","Assalomu Aleykum","$162","$180","10% ON","5,3","(580)"))
        allMainItemListChild.add(AllData(image = "https://www.creditasia.uz/upload/iblock/410/nukd5dp7jaughf6kvdzs448rqe0x2oq3/smartfon-samsung-galaxy-s22-ultra-sm-g908b-ds-128gb-red.jpg","like","Top Seller","Assalomu Aleykum","$162","$180","10% ON","5,3","(580)"))
        allMainItemListChild.add(AllData(image = "https://www.creditasia.uz/upload/iblock/1e0/m6ufo2hs0g9h77fl71w34an3ik2z446l/smartfon-samsung-galaxy-s22-ultra-sm-g908b-ds-256gb-black.jpg","like","Top Seller","Assalomu Aleykum","$162","$180","10% ON","5,3","(580)"))
        allMainItemList.add(CategoryAll(title ="Samsung bradn Telefonlar", listItem = allMainItemListChild))
        allMainItemListChild = ArrayList()
        allMainItemListChild.add(AllData(image = "https://maxcom.uz/storage/product/xFRMVjhHC4UFuVTN2BUg44OhbgWCUCURdKhFd7CC.png","like","Top Seller","Assalomu Aleykum","$162","$180","10% ON","5,3","(580)"))
        allMainItemListChild.add(AllData(image = "https://openshop.uz/uploads/products/photos/7kBzr40Ez7Be8IUe106DiucnkxteXZUWVjHvIEUQ.jpeg","like","Top Seller","Assalomu Aleykum","$162","$180","10% ON","5,3","(580)"))
        allMainItemListChild.add(AllData(image = "https://openshop.uz/uploads/products/photos/LP1jjUwjJygnd93eQUG9zf3O3KIKOIiX.jpg","like","Top Seller","Assalomu Aleykum","$162","$180","10% ON","5,3","(580)"))
        allMainItemListChild.add(AllData(image = "https://kattabozor.s3.eu-central-1.amazonaws.com/ri/e9d85f50ff23b393557b5026ec4268546937eea8a536df8a9293095bf46b77b5_cBzhGm_640l.jpg","like","Top Seller","Assalomu Aleykum","$162","$180","10% ON","5,3","(580)"))
        allMainItemListChild.add(AllData(image = "https://220volt.uz/image/cache/catalog/iPhone/iphone-13pro-Max-1000x1000.jpg","like","Top Seller","Assalomu Aleykum","$162","$180","10% ON","5,3","(580)"))
        allMainItemList.add(CategoryAll(title ="Iphone brand Telefonlar", listItem = allMainItemListChild))
        allMainItemListChild = ArrayList()
        allMainItemListChild.add(AllData(image = "https://i01.appmifile.com/webfile/globalimg/gaoruijia/Phone/G7-grey.png","like","Top Seller","Assalomu Aleykum","$162","$180","10% ON","5,3","(580)"))
        allMainItemListChild.add(AllData(image = "https://openshop.uz/uploads/products/photos/xE05aEvLRYmDcpPIBWeCR0MwqCzg4n8X.jpg","like","Top Seller","Assalomu Aleykum","$162","$180","10% ON","5,3","(580)"))
        allMainItemListChild.add(AllData(image = "https://mi-store.uz/image/cache/catalog/smart/xiaomiredminote9proindia/82e02a4b-c7c8-fd69-a2c7-8e135c1ce017-650x650.png","like","Top Seller","Assalomu Aleykum","$162","$180","10% ON","5,3","(580)"))
        allMainItemListChild.add(AllData(image = "https://i01.appmifile.com/webfile/globalimg/Cindy/J15SGreen.png","like","Top Seller","Assalomu Aleykum","$162","$180","10% ON","5,3","(580)"))
        allMainItemListChild.add(AllData(image = "https://elmakon.uz/images/detailed/10/41.jpeg","like","Top Seller","Assalomu Aleykum","$162","$180","10% ON","5,3","(580)"))
        allMainItemList.add(CategoryAll(title ="Redmi brand Telefonlar", listItem = allMainItemListChild))
        return allMainItemList
    }



    fun LogData(strLog: String){
        Log.e("DataLig", strLog)
    }


    fun getDataSearch():ArrayList<String>{
        listSearch = ArrayList()
        listSearch.add("Erkaklar shimi")
        listSearch.add("Erkaklar ko'ylagi")
        listSearch.add("Erkaklar oyoqkiyimi")
        listSearch.add("Erkaklar shimi")
        listSearch.add("Erkaklar ko'ylagi")
        listSearch.add("Erkaklar oyoqkiyimi")
        listSearch.add("Erkaklar shimi")
        listSearch.add("Erkaklar ko'ylagi")
        listSearch.add("Erkaklar oyoqkiyimi")
        listSearch.add("Erkaklar shimi")
        listSearch.add("Erkaklar ko'ylagi")
        listSearch.add("Erkaklar oyoqkiyimi")
        return listSearch
    }

    fun loadListData(): ArrayList<SearchDataAll>{
        allDataSearch = ArrayList()
        allDataSearch.add(SearchDataAll("https://220volt.uz/image/cache/catalog/iPhone/iphone-13pro-Max-1000x1000.jpg",true,"Allen Solly Regular fit cotton shirt",
            1400000.0,2000000.0,"15% OFF","4.3","(1000)",true
        ))
        allDataSearch.add(SearchDataAll("https://paragraf.uz/wp-content/uploads/2021/12/paragraf.uz-iphone-12-pro-max-blue-kredit-rassrochka-kredit.png",false,"Allen Solly Regular fit cotton shirt",
            1200000.0,1500000.0,"15% OFF","4.3","(1000)",true
        ))
        allDataSearch.add(SearchDataAll("https://kattabozor.s3.eu-central-1.amazonaws.com/ri/5df145366000a5558853fc6fc017dc4443b6190e332805911adc994e6c6fcc51_GqEnyL_640l.jpg",false,"Allen Solly Regular fit cotton shirt",
            3000000.0,3500000.0,"15% OFF","4.3","(1000)",true
        ))
        allDataSearch.add(SearchDataAll("https://openshop.uz/uploads/products/photos/202106/bII3YFYS7lJiO6lCXV5kb68DV23tRzPSSLrRhoXV.jpg",true,"Allen Solly Regular fit cotton shirt",
            35.0,40.25,"15% OFF","4.3","(1000)",true
        ))
        allDataSearch.add(SearchDataAll("https://mi-store.uz/image/cache/catalog/acsess/mitscomputerglasses/kompyuternye_ochki_xiaomi_mijia_ts_turok_steinhardt_anti_blue_glasses_basic_level_fu006-650x650.jpg",false,"Allen Solly Regular fit cotton shirt",
            35.0,40.25,"15% OFF","4.3","(1000)",true
        ))
        allDataSearch.add(SearchDataAll("https://www.creditasia.uz/upload/iblock/dd6/87k877nyaj6urxdlybe1p5dl6rml3l5i/smartfon-samsung-galaxy-a32-sm-a325f-ds-64gb-awesome-white.jpg",false,"Allen Solly Regular fit cotton shirt",
            35.0,40.25,"15% OFF","4.3","(1000)",true
        ))
        allDataSearch.add(SearchDataAll("https://www.notebookcheck-ru.com/uploads/tx_nbc2/XiaomiRedmiNote11_01.JPG",true,"Allen Solly Regular fit cotton shirt",
            35.0,40.25,"15% OFF","4.3","(1000)",true
        ))
        allDataSearch.add(SearchDataAll("https://mi-store.uz/image/cache/catalog/atributy/mielectricscooteressential/scooter-essential-650x650.png",false,"Allen Solly Regular fit cotton shirt",
            35.0,40.25,"15% OFF","4.3","(1000)",true
        ))
        allDataSearch.add(SearchDataAll("https://apollo-olx.cdnvideo.ru/v1/files/gv65nx5efehi2-UZ/image;s=644x461",false,"Allen Solly Regular fit cotton shirt",
            35.0,40.25,"15% OFF","4.3","(1000)",true
        ))
        allDataSearch.add(SearchDataAll("https://olcha.uz/image/340x340/products/lt0548VKV7dvZn87SqBcLIYu9YC0Wh0cYLqILxFARIGNpC66YNUScubJi1Il.jpeg",true,"Allen Solly Regular fit cotton shirt",
            35.0,40.25,"15% OFF","4.3","(1000)",true
        ))
        allDataSearch.add(SearchDataAll("https://devel.prom.uz/upload/reduced/products/cb/62/cb629cf92a933501b4f32551ff953f43.jpg",false,"Allen Solly Regular fit cotton shirt",
            35.0,40.25,"15% OFF","4.3","(1000)",true
        ))
        allDataSearch.add(SearchDataAll("https://avatars.mds.yandex.net/i?id=47bd9a5548d5241fed56d47115d45825-5233602-images-thumbs&n=13",false,"Allen Solly Regular fit cotton shirt",
            35.0,40.25,"15% OFF","4.3","(1000)",true
        ))
   return allDataSearch
    }


    fun loadListDataProduct(): ArrayList<SearchDataAll>{
        allDataSearch = ArrayList()
        allDataSearch.add(SearchDataAll("https://220volt.uz/image/cache/catalog/iPhone/iphone-13pro-Max-1000x1000.jpg",true,"Allen Solly Regular fit cotton shirt",
            35.0,40.25,"15% OFF","4.3","(1000)",true
        ))
        allDataSearch.add(SearchDataAll("https://paragraf.uz/wp-content/uploads/2021/12/paragraf.uz-iphone-12-pro-max-blue-kredit-rassrochka-kredit.png",false,"Allen Solly Regular fit cotton shirt",
            35.0,40.25,"15% OFF","4.3","(1000)",false
        ))
        allDataSearch.add(SearchDataAll("https://kattabozor.s3.eu-central-1.amazonaws.com/ri/5df145366000a5558853fc6fc017dc4443b6190e332805911adc994e6c6fcc51_GqEnyL_640l.jpg",false,"Allen Solly Regular fit cotton shirt",
            35.0,40.25,"15% OFF","4.3","(1000)",false
        ))
        allDataSearch.add(SearchDataAll("https://openshop.uz/uploads/products/photos/202106/bII3YFYS7lJiO6lCXV5kb68DV23tRzPSSLrRhoXV.jpg",true,"Allen Solly Regular fit cotton shirt",
            35.0,40.25,"15% OFF","4.3","(1000)",false
        ))
        allDataSearch.add(SearchDataAll("https://mi-store.uz/image/cache/catalog/acsess/mitscomputerglasses/kompyuternye_ochki_xiaomi_mijia_ts_turok_steinhardt_anti_blue_glasses_basic_level_fu006-650x650.jpg",false,"Allen Solly Regular fit cotton shirt",
            35.0,40.25,"15% OFF","4.3","(1000)",false
        ))
        allDataSearch.add(SearchDataAll("https://www.creditasia.uz/upload/iblock/dd6/87k877nyaj6urxdlybe1p5dl6rml3l5i/smartfon-samsung-galaxy-a32-sm-a325f-ds-64gb-awesome-white.jpg",true,"Allen Solly Regular fit cotton shirt",
            35.0,40.25,"15% OFF","4.3","(1000)",true
        ))
        allDataSearch.add(SearchDataAll("https://www.notebookcheck-ru.com/uploads/tx_nbc2/XiaomiRedmiNote11_01.JPG",true,"Allen Solly Regular fit cotton shirt",
            35.0,40.25,"15% OFF","4.3","(1000)",false
        ))
        allDataSearch.add(SearchDataAll("https://mi-store.uz/image/cache/catalog/atributy/mielectricscooteressential/scooter-essential-650x650.png",false,"Allen Solly Regular fit cotton shirt",
            35.0,40.25,"15% OFF","4.3","(1000)",true
        ))
        allDataSearch.add(SearchDataAll("https://apollo-olx.cdnvideo.ru/v1/files/gv65nx5efehi2-UZ/image;s=644x461",false,"Allen Solly Regular fit cotton shirt",
            35.0,40.25,"15% OFF","4.3","(1000)",false
        ))
        allDataSearch.add(SearchDataAll("https://olcha.uz/image/340x340/products/lt0548VKV7dvZn87SqBcLIYu9YC0Wh0cYLqILxFARIGNpC66YNUScubJi1Il.jpeg",false,"Allen Solly Regular fit cotton shirt",
            35.0,40.25,"15% OFF","4.3","(1000)",false
        ))
        allDataSearch.add(SearchDataAll("https://devel.prom.uz/upload/reduced/products/cb/62/cb629cf92a933501b4f32551ff953f43.jpg",false,"Allen Solly Regular fit cotton shirt",
            35.0,40.25,"15% OFF","4.3","(1000)",false
        ))
        allDataSearch.add(SearchDataAll("https://avatars.mds.yandex.net/i?id=47bd9a5548d5241fed56d47115d45825-5233602-images-thumbs&n=13",false,"Allen Solly Regular fit cotton shirt",
            35.0,40.25,"15% OFF","4.3","(1000)",false
        ))
        return allDataSearch
    }


    fun loadSort():ArrayList<String>{
        var loadSortData = ArrayList<String>()
        loadSortData.add("Price - High to Low")
        loadSortData.add("Price - Low to High")
        loadSortData.add("Popularity")
        loadSortData.add("Discount")
        loadSortData.add("Customer Rating")
        return loadSortData
    }

    fun loadFilter():ArrayList<FilterData>{
        var listFilterData = ArrayList<FilterData>()
        listFilterData.add(FilterData("S",false))
        listFilterData.add(FilterData("S",false))
        listFilterData.add(FilterData("M",false))
        listFilterData.add(FilterData("L",false))
        listFilterData.add(FilterData("XL",true))
        listFilterData.add(FilterData("XXL",false))
        listFilterData.add(FilterData("3XL",false))
        listFilterData.add(FilterData("4XL",false))
        listFilterData.add(FilterData("5XL",false))
        listFilterData.add(FilterData("6XL",false))
        return listFilterData
    }


    fun loadCategoryApp():ArrayList<String>{
        var listCategory = ArrayList<String>()
        listCategory.add("Brand")
        listCategory.add("Size")
        listCategory.add("Categories")
        listCategory.add("Bundles")
        listCategory.add("Price Range")
        listCategory.add("Discount")
        listCategory.add("Rating")
        listCategory.add("Pattern")
        listCategory.add("Sleeve Length")
        listCategory.add("Fit")
        return listCategory
    }

    fun loadCategoryProduct():ArrayList<String>{
        var listCategory = ArrayList<String>()
        listCategory.add("Telefonlar")
        listCategory.add("Uy texnikalari")
        listCategory.add("Categories")
        listCategory.add("Notebooklar")
        listCategory.add("Naushniklar")
        listCategory.add("Sichqonchalar")
        listCategory.add("Sotalar")
        listCategory.add("Planshketlar")
        listCategory.add("Tarozi")
        listCategory.add("Speaker")
        return listCategory
    }

    fun historyAdapter():ArrayList<History>{
        val listHistory = ArrayList<History>()
        listHistory.add(History("Samsung A53","https://www.creditasia.uz/upload/iblock/eeb/1dy3y0jxsuym5c245uzo0nv27o9v20i7/smartfon-samsung-galaxy-a53-5g-sm-a536e-ds-128gb-light-blue.png","Blah Blah Blah","1 400 000 so'm"))
        listHistory.add(History("Samsung A53","https://www.creditasia.uz/upload/iblock/eeb/1dy3y0jxsuym5c245uzo0nv27o9v20i7/smartfon-samsung-galaxy-a53-5g-sm-a536e-ds-128gb-light-blue.png","Blah Blah Blah","1 400 000 so'm"))
        listHistory.add(History("Samsung A53","https://www.creditasia.uz/upload/iblock/eeb/1dy3y0jxsuym5c245uzo0nv27o9v20i7/smartfon-samsung-galaxy-a53-5g-sm-a536e-ds-128gb-light-blue.png","Blah Blah Blah","1 400 000 so'm"))
        listHistory.add(History("Samsung A53","https://www.creditasia.uz/upload/iblock/eeb/1dy3y0jxsuym5c245uzo0nv27o9v20i7/smartfon-samsung-galaxy-a53-5g-sm-a536e-ds-128gb-light-blue.png","Blah Blah Blah","1 400 000 so'm"))
        listHistory.add(History("Samsung A53","https://www.creditasia.uz/upload/iblock/eeb/1dy3y0jxsuym5c245uzo0nv27o9v20i7/smartfon-samsung-galaxy-a53-5g-sm-a536e-ds-128gb-light-blue.png","Blah Blah Blah","1 400 000 so'm"))
        listHistory.add(History("Samsung A53","https://www.creditasia.uz/upload/iblock/eeb/1dy3y0jxsuym5c245uzo0nv27o9v20i7/smartfon-samsung-galaxy-a53-5g-sm-a536e-ds-128gb-light-blue.png","Blah Blah Blah","1 400 000 so'm"))
        listHistory.add(History("Samsung A53","https://www.creditasia.uz/upload/iblock/eeb/1dy3y0jxsuym5c245uzo0nv27o9v20i7/smartfon-samsung-galaxy-a53-5g-sm-a536e-ds-128gb-light-blue.png","Blah Blah Blah","1 400 000 so'm"))
        listHistory.add(History("Samsung A53","https://www.creditasia.uz/upload/iblock/eeb/1dy3y0jxsuym5c245uzo0nv27o9v20i7/smartfon-samsung-galaxy-a53-5g-sm-a536e-ds-128gb-light-blue.png","Blah Blah Blah","1 400 000 so'm"))
        listHistory.add(History("Samsung A53","https://www.creditasia.uz/upload/iblock/eeb/1dy3y0jxsuym5c245uzo0nv27o9v20i7/smartfon-samsung-galaxy-a53-5g-sm-a536e-ds-128gb-light-blue.png","Blah Blah Blah","1 400 000 so'm"))
        listHistory.add(History("Samsung A53","https://www.creditasia.uz/upload/iblock/eeb/1dy3y0jxsuym5c245uzo0nv27o9v20i7/smartfon-samsung-galaxy-a53-5g-sm-a536e-ds-128gb-light-blue.png","Blah Blah Blah","1 400 000 so'm"))
        listHistory.add(History("Samsung A53","https://www.creditasia.uz/upload/iblock/eeb/1dy3y0jxsuym5c245uzo0nv27o9v20i7/smartfon-samsung-galaxy-a53-5g-sm-a536e-ds-128gb-light-blue.png","Blah Blah Blah","1 400 000 so'm"))
        return listHistory
    }

}

