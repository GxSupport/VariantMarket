package uz.gxteam.variantmarket.utils.composition

import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.viewpager2.widget.ViewPager2
import uz.gxteam.variantmarket.utils.dialogHelper.DialogHelper
import uz.gxteam.variantmarket.utils.screenNavigate.ScreenNavigate
import uz.gxteam.variantmarket.utils.uiController.UiController


class AppCompositionRoot(
    private val activity: AppCompatActivity,
    private val navController: NavController,
    private val owner: LifecycleOwner,
    private val uiController: UiController
) {

    var viewPager2:ViewPager2?=null


    val lifsycleOwner get() = owner
    val activityApp get() = activity
    val screenNavigate by lazy {
        ScreenNavigate(navController)
    }
    val uiControllerApp get() = uiController

    val dialogHelper by lazy {
        DialogHelper(activityApp.applicationContext)
    }


    fun shareData(){
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
        sendIntent.type = "text/plain"
        activityApp.startActivity(sendIntent)
    }


    fun viewPager2Animation(viewPager2: ViewPager2){
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

    }

    fun drawableColorUpdate(view: View, color:Int){
        val gradientDrawable = GradientDrawable()
        gradientDrawable.shape = GradientDrawable.RECTANGLE
        gradientDrawable.cornerRadius = 20f
        gradientDrawable.setColor(
            ContextCompat.getColor(activity, color)
        )
        view.background = gradientDrawable
    }
}