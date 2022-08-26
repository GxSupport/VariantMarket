package uz.gxteam.variantmarket.utils.extensions

import android.annotation.SuppressLint
import android.graphics.Color
import android.icu.text.NumberFormat
import android.os.Build
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.viewbinding.ViewBinding
import coil.dispose
import coil.load
import coil.request.ImageRequest
import com.github.ybq.android.spinkit.SpinKitView
import com.google.android.material.bottomsheet.BottomSheetDialog
import uz.gxteam.variantmarket.R
import uz.gxteam.variantmarket.adapters.genericAdapter.AdapterGeneric
import uz.gxteam.variantmarket.databinding.BottomSheetDialogBinding
import java.util.*

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



@SuppressLint("NewApi")
fun Double.format():String {
    return NumberFormat.getNumberInstance(Locale.getDefault()).format(this)
}