package uz.gxteam.variantmarket.utils.extensions

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.icu.text.NumberFormat
import android.os.Build
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import androidx.viewbinding.ViewBinding
import coil.load
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import kotlinx.coroutines.flow.StateFlow
import uz.gxteam.variantmarket.R
import uz.gxteam.variantmarket.databinding.BottomSheetDialogBinding
import uz.gxteam.variantmarket.utils.language.LocaleManager
import uz.gxteam.variantmarket.utils.responseState.ResponseState
import uz.gxteam.variantmarket.utils.uiController.UiController
import java.util.*

suspend inline fun <reified T> StateFlow<ResponseState<T>>.fetchResult(
    uiController: UiController,
    crossinline invokeSuccess: (T) -> Unit,
    crossinline errorData:(errorCode:Int,errorMessage:String)->Unit
) {
    var errorCount = 0
    this.collect { result ->
        when (result) {
            is ResponseState.Loading -> {
                uiController.showLoading()
            }
            is ResponseState.Success -> {
                uiController.hideLoading()

                val parseData = result.data?.parseJsonInClass(T::class.java)
                invokeSuccess.invoke(parseData!!)
            }
            is ResponseState.Error -> {
                Log.e("ErrorData___", result.errorMessage.toString())
                uiController.hideLoading()
                if (errorCount==0){
                    errorData.invoke(result.errorCode?:0,result.errorMessage.toString())
                    errorCount++
                }
            }
        }
    }
}



inline fun <reified T> Any.parseJsonInClass(classData:Class<T>):T{
    val gson = GsonBuilder()
    val toJson = gson.create().toJson(this)
    var data = gson.create().fromJson(toJson.toString(),classData)
    return data
}
inline fun Any.gsonData():String{
    val gson = GsonBuilder()
    return gson.create().toJson(this)
}

fun <T> JsonElement.parseClass(classData:Class<T>):T{
    val gson = Gson()
    return gson.fromJson(this,classData)
}


fun logData(message:String){
    Log.e("VariantMarket_log_e", message)
}


fun RadioButton.checked(){
    this.isChecked = true
}


fun RadioButton.noChecked(){
    this.isChecked = false
}

fun TextView.textApp(str:String){
    this.text = str
}

fun String.isNotNullOrEmpty():Boolean{
    return this!=null && this.isNotEmpty() && this!=""
}

fun View.visible(){
    this.isVisible = true
}
fun View.gone(){
    this.isVisible = false
}

fun View.enabled(){
    this.isEnabled = true
}
fun View.enabledFalse(){
    this.isEnabled = false
}


fun String?.isNotEmptyOrNull():Boolean{
    return this != null && this.isNotEmpty() && this != ""
}

fun String.colorParse():Int{
    return Color.parseColor(this)
}


fun ImageView.dataImage(imageUrl:String,viewData:(isCreate:Boolean)->Unit){
    this.load(imageUrl){
        viewData.invoke(false)
        placeholder(R.drawable.plase_holder)
        crossfade(true)
        crossfade(400)
    }
    viewData.invoke(true)
}

fun ImageView.imageData(url:String,context: Context){
    val circularProgressDrawable = CircularProgressDrawable(context)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()
    circularProgressDrawable.setColorSchemeColors(ContextCompat.getColor(context, R.color.strocke_color))
    Glide.with(context)
        .load(url)
        .placeholder(circularProgressDrawable)
        .into(this)
}

fun BottomSheetDialog.createData(@LayoutRes layoutRes:Int, onClick:(vb:ViewBinding)->Unit){
    var loadAnimation1 = AnimationUtils.loadAnimation(context, R.anim.anim_view)
    when(layoutRes){
        R.layout.bottom_sheet_dialog->{
            val bottomSheetBinding = BottomSheetDialogBinding.inflate(layoutInflater)
            this.setContentView(bottomSheetBinding.root)
            bottomSheetBinding.textSort.animation = loadAnimation1
            bottomSheetBinding.cancel.animation = loadAnimation1
            bottomSheetBinding.cancel.setOnClickListener {
                this.dismiss()
            }
            onClick.invoke(bottomSheetBinding)
        }
    }
    this.show()
}


fun <A: Activity> Activity.startNewActivity(activity: Class<A>){
    Intent(this,activity).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK.or(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(it)
    }
}

fun getLanguage(context: Context):String{
    // ru en uz
    return LocaleManager.getLanguage(context)
}

@SuppressLint("NewApi")
fun Double.format():String {
    return NumberFormat.getNumberInstance(Locale.getDefault()).format(this)
}


fun Activity.transparentStatusAndNavigation(
    systemUiScrim: Int = Color.parseColor("#40000000") // 25% black
) {
    var systemUiVisibility = 0
    // Use a dark scrim by default since light status is API 23+
    var statusBarColor = systemUiScrim
    //  Use a dark scrim by default since light nav bar is API 27+
    var navigationBarColor = systemUiScrim
    val winParams = window.attributes


    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        systemUiVisibility = systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        statusBarColor = Color.TRANSPARENT
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        systemUiVisibility = systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        navigationBarColor = Color.TRANSPARENT
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        systemUiVisibility = systemUiVisibility or
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        window.decorView.systemUiVisibility = systemUiVisibility
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
        winParams.flags = winParams.flags or
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        winParams.flags = winParams.flags and
                (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or
                        WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION).inv()
        window.statusBarColor = statusBarColor
        window.navigationBarColor = navigationBarColor
    }

    window.attributes = winParams
}