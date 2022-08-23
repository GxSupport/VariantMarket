package uz.gxteam.variantmarket.presentation.ui.cateGoryData

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import uz.gxteam.variantmarket.R
import uz.gxteam.variantmarket.adapters.advertising.AdvertisingAdapter
import uz.gxteam.variantmarket.adapters.genericAdapter.AdapterGeneric
import uz.gxteam.variantmarket.adapters.viewPager.ViewPagerAdapter
import uz.gxteam.variantmarket.databinding.FragmentCategoryBinding
import uz.gxteam.variantmarket.databinding.FragmentCategoryDataBinding
import uz.gxteam.variantmarket.databinding.TabLayoutItemBinding
import uz.gxteam.variantmarket.models.simpleSlide.SlideData
import uz.gxteam.variantmarket.presentation.activitys.MainActivity
import uz.gxteam.variantmarket.presentation.ui.base.BaseFragment
import uz.gxteam.variantmarket.utils.extensions.textApp
import java.util.ArrayList

class CategoryDataFragment : BaseFragment<FragmentCategoryDataBinding>() {
    private var name:String?=null
    private var pos:Int?=null
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
        appCompositionRoot.activityApp.supportActionBar?.show()
       binding.apply {
           viewPager2Data()
           viewPagerCatData()
           TabLayoutMediator(tabLayout,viewPagerCat){ tab,position->
               val binding = TabLayoutItemBinding.inflate(appCompositionRoot.activityApp.layoutInflater)
               binding.textData.textApp( loadCategoryProduct()[position])
               if (position == 0) {
                  appCompositionRoot.drawableColorUpdate(binding.linearItem,R.color.app_background)
                   binding.textData.setTextColor(Color.WHITE)
               }else{
                   binding.linearItem.setBackgroundResource(R.drawable.tab_item_back)
               }
               tab.customView = binding.root
           }.attach()
           (appCompositionRoot.activityApp as MainActivity).toolbarTitle(name.toString())
           tabLayout.clipChildren = false
           tabLayout.clipToPadding = false
           viewPagerCat.clipChildren = false
           viewPagerCat.clipToPadding = false
       }
    }

    private fun viewPagerCatData() {
        viewPagerAdapter = ViewPagerAdapter(loadCategoryProduct(),appCompositionRoot.activityApp)
        binding.viewPagerCat.adapter = viewPagerAdapter
        binding.viewPagerCat.registerOnPageChangeCallback(pageChange(loadCategoryProduct()))
    }

    private fun pageChange(loadCategoryProduct: ArrayList<String>): ViewPager2.OnPageChangeCallback {
        return object:ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                var tabCount = binding.tabLayout.tabCount
                for (i in 0 until tabCount){
                    binding.tabLayout.getTabAt(i).let {
                        val binding = it?.customView?.let { it1 -> TabLayoutItemBinding.bind(it1) }
                        if(binding!=null){
                            binding.textData.setTextColor(Color.BLACK)
                            val gradientDrawable = binding.linearItem.background.mutate() as GradientDrawable
                            gradientDrawable.setColor(ContextCompat.getColor(appCompositionRoot.activityApp,R.color.white))
                            gradientDrawable.setStroke(2,ContextCompat.getColor(appCompositionRoot.activityApp,R.color.app_background))
                        }
                    }
                }
                binding.tabLayout.getTabAt(position).let {
                    val binding = it?.customView?.let { it1 -> TabLayoutItemBinding.bind(it1) }
                    if (binding!=null){
                        val gradientDrawable = binding.linearItem.background.mutate() as GradientDrawable
                        gradientDrawable.setColor(ContextCompat.getColor(appCompositionRoot.activityApp,R.color.app_background))
                        binding.textData.setTextColor(Color.WHITE)
                    }
                }
            }
        }
    }

    override fun start(savedInstanceState: Bundle?) {
        arguments.apply {
            name = this?.getString("name")
            pos = this?.getInt("position",0)
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
}