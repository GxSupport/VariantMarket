package uz.gxteam.variantmarket.utils.composition

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
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



}