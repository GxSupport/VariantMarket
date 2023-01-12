package uz.gxteam.variantmarket.presentation.ui.cateGoryData

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import uz.gxteam.variantmarket.R
import uz.gxteam.variantmarket.adaptersLocale.advertising.AdvertisingAdapter
import uz.gxteam.variantmarket.adaptersLocale.viewPager.ViewPagerAdapter
import uz.gxteam.variantmarket.databinding.FragmentCategoryDataBinding
import uz.gxteam.variantmarket.databinding.TabLayoutItemBinding
import uz.gxteam.variantmarket.models.categoryModel.CategoryModel
import uz.gxteam.variantmarket.models.local.simpleSlide.SlideData
import uz.gxteam.variantmarket.presentation.activitys.MainActivity
import uz.gxteam.variantmarket.presentation.ui.base.BaseFragment
import uz.gxteam.variantmarket.utils.appConstant.AppConstant.CATEGORY_ID
import uz.gxteam.variantmarket.utils.appConstant.AppConstant.CATEGORY_NAME
import uz.gxteam.variantmarket.utils.appConstant.AppConstant.EN
import uz.gxteam.variantmarket.utils.appConstant.AppConstant.RU
import uz.gxteam.variantmarket.utils.appConstant.AppConstant.UZB
import uz.gxteam.variantmarket.utils.extensions.*
import uz.gxteam.variantmarket.utils.responseState.ResponseState
import uz.gxteam.variantmarket.viewModels.categoryDataVm.CategoryDataViewModel

@AndroidEntryPoint
class CategoryDataFragment : BaseFragment<FragmentCategoryDataBinding>() {
    // categoryData ViewModel
    private val categoryDataViewModel:CategoryDataViewModel by viewModels()
    private var name:String?=null
    private var categoryId:Int?=null

    private var hasMotionScrolled = false
    private lateinit var advertisingAdapter: AdvertisingAdapter
    private val slideRunnable:Runnable = Runnable {
        try {
            binding.viewPager2.currentItem = binding.viewPager2.currentItem+1
        }catch (e:Exception){
            e.printStackTrace()
        }
    }
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun setup(savedInstanceState: Bundle?) {
        (activity as MainActivity).toolbarTitle(name.toString())
        appCompositionRoot.activityApp.supportActionBar?.show()
       binding.apply {

           getChildCategory()

           viewPager2Data()

           tabLayout.clipChildren = false
           tabLayout.clipToPadding = false
           viewPagerCat.clipChildren = false
           viewPagerCat.clipToPadding = false
       }
    }



    private fun getChildCategory(){
        categoryDataViewModel.getCategory(categoryId?:0)
        lifecycleScope.launchWhenCreated {
            categoryDataViewModel.categoryData.collect { result->
                when(result){
                    is ResponseState.Loading->{
                        binding.includeShimmer.shimmerData.visible()
                    }
                    is ResponseState.Success->{
                        binding.includeShimmer.shimmerData.gone()
                        val categoryModel = result.data?.parseClass(CategoryModel::class.java)
                        viewPagerCatData(categoryModel)

                        TabLayoutMediator(binding.tabLayout,binding.viewPagerCat){ tab,position->
                            val binding = TabLayoutItemBinding.inflate(appCompositionRoot.activityApp.layoutInflater)
                            when(getLanguage(requireContext())){
                                UZB->{
                                    binding.textData.textApp(categoryModel?.success?.get(position)?.name_uz.toString())
                                }
                                RU->{
                                    binding.textData.textApp(categoryModel?.success?.get(position)?.name_ru.toString())
                                }
                                EN->{
                                    binding.textData.textApp(categoryModel?.success?.get(position)?.name_uzc.toString())
                                }
                            }
                            if (position == 0) {
                                appCompositionRoot.drawableColorUpdate(binding.linearItem,R.color.app_background)
                                binding.textData.setTextColor(ContextCompat.getColor(requireContext(),R.color.strocke_color))
                            }else{
                                binding.linearItem.setBackgroundResource(R.drawable.tab_item_back)
                            }
                            tab.customView = binding.root
                        }.attach()
                    }
                    is ResponseState.Error->{
                        binding.includeShimmer.shimmerData.gone()
                        appCompositionRoot.errorDialog(result.errorCode?:0,result.errorMessage.toString()){
                            if (it) getChildCategory()
                        }
                    }
                }
            }
        }
    }




    private fun viewPagerCatData(categoryModel:CategoryModel?) {
        viewPagerAdapter = ViewPagerAdapter(categoryModel?.success?:emptyList(),appCompositionRoot.activityApp)
        binding.viewPagerCat.adapter = viewPagerAdapter
        binding.viewPagerCat.registerOnPageChangeCallback(pageChange())
    }

    private fun pageChange(): ViewPager2.OnPageChangeCallback {
        return object:ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                var tabCount = binding.tabLayout.tabCount
                for (i in 0 until tabCount){
                    binding.tabLayout.getTabAt(i).let {
                        val binding = it?.customView?.let { it1 -> TabLayoutItemBinding.bind(it1) }
                        if(binding!=null){
                            val gradientDrawable = binding.linearItem.background.mutate() as GradientDrawable
                            gradientDrawable.setColor(ContextCompat.getColor(appCompositionRoot.activityApp,R.color.item_tab_color_in))
                            gradientDrawable.setStroke(2,ContextCompat.getColor(appCompositionRoot.activityApp,R.color.item_tab_color_in))
                            binding.textData.setTextColor(ContextCompat.getColor(requireContext(),R.color.text_color_app))
                        }
                    }
                }
                binding.tabLayout.getTabAt(position).let {
                    val binding = it?.customView?.let { it1 -> TabLayoutItemBinding.bind(it1) }
                    if (binding!=null){
                        val gradientDrawable = binding.linearItem.background.mutate() as GradientDrawable
                        gradientDrawable.setColor(ContextCompat.getColor(appCompositionRoot.activityApp,R.color.item_tab_color_in))
                        gradientDrawable.setStroke(2,ContextCompat.getColor(appCompositionRoot.activityApp,R.color.strocke_color))
                        binding.textData.setTextColor(ContextCompat.getColor(requireContext(),R.color.strocke_color))
                    }
                }
            }
        }
    }

    override fun start(savedInstanceState: Bundle?) {
        arguments.apply {
            name = this?.getString(CATEGORY_NAME)
            categoryId = this?.getInt(CATEGORY_ID,0)
        }
    }


    // TODO: ViewPager 2 animation and data
    fun viewPager2Data(){
        binding.apply {
            advertisingAdapter = AdvertisingAdapter(loadAdvertising(),object:AdvertisingAdapter.OnItemClikcListener{
                override fun onItemClick(slideData: SlideData, position: Int) {

                }
            })
            viewPager2.adapter = advertisingAdapter
            springDotsIndicator.attachTo(viewPager2)
            appCompositionRoot.viewPager2Animation(viewPager2)
            viewPager2.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    // TODO: try catch ViewPager position
                        handler.removeCallbacks(slideRunnable)
                        handler.postDelayed(slideRunnable,3000)
                    try {
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
        }
    }
    companion object{
        private const val MOTION_TRANSITION_COMPLETED = 1F
        private const val MOTION_TRANSITION_INITIAL = 0F
    }

    override fun onResume() {
        super.onResume()
        if (hasMotionScrolled) binding.motion.progress = MOTION_TRANSITION_COMPLETED
    }

    override fun onPause() {
        super.onPause()
        hasMotionScrolled = binding.motion.progress > MOTION_TRANSITION_INITIAL
    }

    override fun inflateViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentCategoryDataBinding =
        FragmentCategoryDataBinding.inflate(inflater,container,false)
}