package uz.gxteam.variantmarket.utils.composition

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.snackbar.Snackbar
import uz.gxteam.variantmarket.R
import uz.gxteam.variantmarket.databinding.LoadingBinding
import uz.gxteam.variantmarket.utils.dialogHelper.DialogHelper
import uz.gxteam.variantmarket.utils.screenNavigate.ScreenNavigate
import uz.gxteam.variantmarket.utils.sharedPreferences.MySharedPreferences
import uz.gxteam.variantmarket.utils.uiController.UiController
import uz.gxteam.variantmarket.viewModels.mainViewModel.MainViewModel


class AppCompositionRoot(
    private val activity: AppCompatActivity,
    val navController: NavController,
    private val owner: LifecycleOwner,
    private val uiController: UiController,
    private val mainViewModel: MainViewModel?=null
) {

    var viewPager2:ViewPager2?=null
    val mainViewModelApp:MainViewModel get() = mainViewModel!!
    val lifsycleOwner get() = owner
    val activityApp get() = activity
    val screenNavigate by lazy {
        ScreenNavigate(navController)
    }
    val uiControllerApp get() = uiController

    private val dialogHelper by lazy {
        DialogHelper(activity,activityApp.applicationContext)
    }



    fun bottomSheetDialogData(layoutRes:Int,mySharedPreferences: MySharedPreferences){
        dialogHelper.bottomSheetDialog(layoutRes,mySharedPreferences)
    }


    fun errorDialog(
        errorCode:Int,
        errorMessage:String,
        onCLick:(isClick:Boolean)->Unit
    ){
        dialogHelper.errorDialog(errorCode,errorMessage,onCLick)
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




    fun navigationBarColor(color: Int){
        activityApp.window.navigationBarColor = ContextCompat.getColor(activity,color)
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

    fun snackBarData(view:View,data:String,cancelClick:()->Unit){
        Snackbar.make(view, data, Snackbar.LENGTH_SHORT)
            .setAction(R.string.cancel) {
                cancelClick.invoke()
            }
            .setBackgroundTint(ContextCompat.getColor(activity, R.color.color_snack_bar))
            .setActionTextColor(ContextCompat.getColor(activity, R.color.white)).show()
    }

    fun toolbarLeftIcon(){
        if(activity.supportActionBar?.isShowing==false){
            activityApp.supportActionBar?.show()
        }
        activityApp.supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_left)
    }

    val alertDialog = AlertDialog.Builder(activity)
    val create = alertDialog.create()
    fun loadingDialog(isLoading:Boolean){
        if (isLoading){
            val loading = LoadingBinding.inflate(activityApp.layoutInflater)
            create.setView(loading.root)
            create.show()
        }else{
            create.dismiss()
        }
        create.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        create.setCancelable(false)
    }
}