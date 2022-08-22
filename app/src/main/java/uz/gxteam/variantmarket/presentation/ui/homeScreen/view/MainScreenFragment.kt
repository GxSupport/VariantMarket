package uz.gxteam.variantmarket.presentation.ui.homeScreen.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.viewpager2.widget.ViewPager2
import uz.gxteam.variantmarket.R
import uz.gxteam.variantmarket.adapters.adapterAll.AllItemAdapter
import uz.gxteam.variantmarket.adapters.advertising.AdvertisingAdapter
import uz.gxteam.variantmarket.adapters.discount.DiscountAdapter
import uz.gxteam.variantmarket.adapters.genericAdapter.AdapterGeneric
import uz.gxteam.variantmarket.databinding.FragmentMainScreenBinding
import uz.gxteam.variantmarket.models.allM.AllData
import uz.gxteam.variantmarket.models.allM.CategoryAll
import uz.gxteam.variantmarket.models.discount.Discount
import uz.gxteam.variantmarket.models.newsData.NewsData
import uz.gxteam.variantmarket.models.simpleCategory.Category
import uz.gxteam.variantmarket.models.simpleSlide.SlideData
import uz.gxteam.variantmarket.presentation.ui.base.BaseFragment
import uz.gxteam.variantmarket.utils.AppConstant.DISCOUNT_POS
import uz.gxteam.variantmarket.utils.AppConstant.OB_POS
import uz.gxteam.variantmarket.utils.AppConstant.PRODUCT_CATEGORY

class MainScreenFragment : BaseFragment<FragmentMainScreenBinding>(){
    private lateinit var listCategory:ArrayList<Category>
    private lateinit var advertisingAdapter:AdvertisingAdapter
    private lateinit var discountAdapter: DiscountAdapter
    private lateinit var allItemAdapter: AllItemAdapter
    private lateinit var categoryAdapter:AdapterGeneric<Category>
    private lateinit var adapterGenericNews:AdapterGeneric<NewsData>

    override fun setup(savedInstanceState: Bundle?) {
        binding.apply {
            val loadAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.anim_view)
            startShimmer()

            Handler(Looper.getMainLooper()).postDelayed({
                stopShimmer()
                createView()
                // TODO: Animation Mainiew
                generateAnimation(loadAnimation)
            },2000)


            swipeRefresh.setOnRefreshListener {
                goneView()
                startShimmer()
                Handler(Looper.getMainLooper()).postDelayed({
                    swipeRefresh.isRefreshing = false
                    stopShimmer()
                    createView()
                    generateAnimation(loadAnimation)
                    viewGenerate(loadAnimation)
                },1500)
            }
            viewGenerate(loadAnimation)
            categoryTableView.setOnClickListener {
                if (appCompositionRoot.viewPager2!=null){
                    appCompositionRoot.viewPager2?.setCurrentItem(1,false)
                }
            }

            categoryTableView1.setOnClickListener {
                appCompositionRoot.screenNavigate.createContainerProduct("Chegirma elon qilingan mahsulotlar",DISCOUNT_POS)
            }
        }
    }

    override fun start(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        loadCategoryData()
    }




    fun viewGenerate(loadAnimation:Animation){
        binding.apply {
            try {
                searchView.setOnClickListener {
                    appCompositionRoot.screenNavigate.createSearchDataView()
                }
            }catch (e:IllegalStateException){
                // TODO: Error IllegalStateException
                LogData(e.message.toString())
            }

            categoryTableView2.setOnClickListener {
                appCompositionRoot.screenNavigate.createContainerProduct(newProduct.text.toString().trim(),0)
            }

            // categoryAdapter
            // TODO:  categoryAdapter
            categoryAdapter = AdapterGeneric(R.layout.item_category,listCategory){category, position ->
              appCompositionRoot.screenNavigate.createContainerProduct(category.name,OB_POS)
            }
            rvCategory.adapter = categoryAdapter
            // advertisingAdapter
            advertisingAdapter = AdvertisingAdapter(loadAdvertising(),object:AdvertisingAdapter.OnItemClikcListener{
                override fun onItemClick(slideData: SlideData, position: Int) {
                    appCompositionRoot.screenNavigate.createDataProdInfo()
                }
            })
            viewPager2.adapter = advertisingAdapter
            springDotsIndicator.attachTo(viewPager2)
            viewPager2.setPageTransformer { page, position ->
                if (position < -1) {    // [-Infinity,-1)
                    // This page is way off-screen to the left.
                    page.alpha = 0F
                } else if (position <= 0) {    // [-1,0]
                    page.alpha = 1F
                    page.pivotX = page.width.toFloat()
                    page.rotationY = -90 * kotlin.math.abs(position)
                } else if (position <= 1) {    // (0,1]
                    page.alpha = 1F
                    page.pivotX = 0F
                    page.rotationY = 90 * kotlin.math.abs(position)
                } else {    // (1,+Infinity]
                    // This page is way off-screen to the right.
                    page.alpha = 0F
                }
            }

            viewPager2.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    // TODO: try catch ViewPager position
                    try {
                        handler.removeCallbacks(slideRunnable)
                        handler.postDelayed(slideRunnable,3000)
                        if (position == loadAdvertising().size-1){
                            handler.postDelayed({
                                binding.viewPager2.setCurrentItem(0,false)
                            },3000)
                        }
                    }catch (e:Exception){
                        e.printStackTrace()
                    }


                }
            })
            viewPager2.animation = loadAnimation


            discountAdapter = DiscountAdapter(object:DiscountAdapter.OnItemClickListener{
                override fun onDiscountClick(discount: Discount, position: Int) {
                    appCompositionRoot.screenNavigate.createDataProdInfo()
                }
            })
            discountAdapter.submitList(loadDiscount())
            rvDiscount.adapter = discountAdapter
            rvDiscount.isNestedScrollingEnabled = false

            // MainView Views items Adapter
            allItemAdapter = AllItemAdapter(object:AllItemAdapter.OnItemClickListener{
                override fun onItemClick(allData: AllData, position: Int) {
                    appCompositionRoot.screenNavigate.createDataProdInfo()
                }

                override fun onItemClickLike(allData: AllData, position: Int) {

                }

                override fun onItemClickAll(categoryAll: CategoryAll, position: Int) {
                    appCompositionRoot.screenNavigate.createContainerProduct(categoryAll.title,PRODUCT_CATEGORY)
                }
            })
            allItemAdapter.submitList(getMainListData())
            rvItem.adapter = allItemAdapter
            // TODO: NewsAdapter
            adapterGenericNews = AdapterGeneric(R.layout.item_new_product, getNewsData()){ news, position ->
                appCompositionRoot.screenNavigate.createDataProdInfo()
            }
            rvNewProduct.adapter = adapterGenericNews
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_home,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_home->{
                Log.e("ItemMenu One", "onOptionsItemSelected: ", )
            }
            R.id.nav_gallery->{
                Log.e("ItemMenu Two", "onOptionsItemSelected: ", )
            }
        }
        return super.onOptionsItemSelected(item)
    }




    private val slideRunnable:Runnable = Runnable {
        try {
            binding.viewPager2.currentItem = binding.viewPager2.currentItem+1
        }catch (e:Exception){
           e.printStackTrace()
        }
    }

    fun startShimmer(){
        binding.include.shimmer.startShimmer()
    }

    fun stopShimmer(){
        binding.include.shimmer.stopShimmer()
    }

    fun createView(){
        binding.include.shimmer.visibility = View.GONE
        binding.nestedApp.visibility = View.VISIBLE
    }


    fun goneView(){
        binding.include.shimmer.visibility = View.VISIBLE
        binding.nestedApp.visibility = View.GONE
    }

    fun generateAnimation(loadAnimation:Animation){
        binding.apply {
            searchInput.animation = loadAnimation
            categoryTableView.animation = loadAnimation
            categoryTableView1.animation = loadAnimation
            springDotsIndicator.animation = loadAnimation
            timeCard.animation = loadAnimation
            cardDiscount.animation = loadAnimation
            viewItem.animation = loadAnimation
        }
    }


    fun loadCategoryData(){
        listCategory = ArrayList()
        listCategory.add(Category(name = "Fashion", image = R.drawable.ic_shirt, color = "#3C2DD4BF"))
        listCategory.add(Category(name = "Electronics", image = R.drawable.ic_electron, color = "#5BFB923C"))
        listCategory.add(Category(name = "Appliances", image = R.drawable.ic_appliances, color = "#3E34D399"))
        listCategory.add(Category(name = "Beauty", image = R.drawable.ic_pamad, color = "#4122D3EE"))
        listCategory.add(Category(name = "Furniture", image = R.drawable.ic_furniture, color = "#3E3B82F6"))
    }
}