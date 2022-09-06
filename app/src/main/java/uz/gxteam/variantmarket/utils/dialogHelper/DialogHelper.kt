package uz.gxteam.variantmarket.utils.dialogHelper

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import uz.gxteam.variantmarket.R
import uz.gxteam.variantmarket.databinding.ErrorDialogBinding
import uz.gxteam.variantmarket.databinding.LanguageDialogBinding
import uz.gxteam.variantmarket.databinding.UserInfoDialogBinding
import uz.gxteam.variantmarket.utils.AppConstant.EN
import uz.gxteam.variantmarket.utils.AppConstant.END_CLIENT_ERROR
import uz.gxteam.variantmarket.utils.AppConstant.END_SERVER_ERROR
import uz.gxteam.variantmarket.utils.AppConstant.NO_INTERNET_CONNECTION
import uz.gxteam.variantmarket.utils.AppConstant.RU
import uz.gxteam.variantmarket.utils.AppConstant.START_CLIENT_ERROR
import uz.gxteam.variantmarket.utils.AppConstant.START_SERVER_ERROR
import uz.gxteam.variantmarket.utils.AppConstant.UZB
import uz.gxteam.variantmarket.utils.extensions.checked
import uz.gxteam.variantmarket.utils.extensions.textApp
import uz.gxteam.variantmarket.utils.language.LocaleManager
import uz.gxteam.variantmarket.utils.sharedPreferences.MySharedPreferences

class DialogHelper(
    private val activity:AppCompatActivity,
    private val context: Context
) {
    fun errorDialog(
        errorCode:Int,
        errorMessage: String,
        onCLick:(isClick:Boolean)->Unit
    ){
        var alertDialog = AlertDialog.Builder(activity)
        val create = alertDialog.create()
        val binding = ErrorDialogBinding.inflate(activity.layoutInflater)
        create.setView(binding.root)
        when(errorCode){
            NO_INTERNET_CONNECTION->{
                binding.lottie.setAnimation(R.raw.no_internet)
                binding.textErrorData.textApp(activity.getString(R.string.no_internet))
            }
            in START_CLIENT_ERROR..END_CLIENT_ERROR->{
                binding.textErrorData.textApp(errorMessage)
            }
            in START_SERVER_ERROR..END_SERVER_ERROR->{
                binding.lottie.setAnimation(R.raw.server_error)
                binding.textErrorData.textApp(activity.getString(R.string.error_server))
            }
            else->{
                binding.textErrorData.textApp(errorMessage)
            }
        }
        binding.okBtn.setOnClickListener {
            onCLick.invoke(true)
            create.dismiss()
        }
        binding.cancelBtn.setOnClickListener {
            onCLick.invoke(false)
            create.dismiss()
        }
        create.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        create.show()
    }

    fun bottomSheetDialog(layoutRes:Int,mySharedPreferences: MySharedPreferences){
        val bottomSheetDialog = BottomSheetDialog(activity,R.style.BottomSheetDialogThem)
        when(layoutRes){
            R.layout.user_info_dialog->{
                userDataUpdateOrSave(bottomSheetDialog)
            }
            R.layout.language_dialog->{
                languageUpdate(bottomSheetDialog,mySharedPreferences)
            }
        }
        bottomSheetDialog.show()
    }

    // TODO: userData Bottom
    fun userDataUpdateOrSave(bottomSheetDialog: BottomSheetDialog){
        val binding = UserInfoDialogBinding.inflate(activity.layoutInflater)
        bottomSheetDialog.setContentView(binding.root)
        binding.editBtn.setOnClickListener {
            bottomSheetDialog.dismiss()
        }
    }
    // TODO: language data
    fun languageUpdate(bottomSheetDialog: BottomSheetDialog,mySharedPreferences: MySharedPreferences){

        val binding = LanguageDialogBinding.inflate(activity.layoutInflater)
        bottomSheetDialog.setContentView(binding.root)
        when(LocaleManager.getLanguage(context)?.lowercase()){
            UZB.lowercase()->{
                binding.uz.checked()
            }
            RU.lowercase()->{
                binding.ru.checked()
            }
            EN.lowercase()->{
                binding.cr.checked()
            }
        }

        binding.saveBtn.setOnClickListener {
            when(binding.group.checkedRadioButtonId){
                R.id.uz->{
                    LocaleManager.setNewLocale(context,UZB)
                }
                R.id.ru->{
                    LocaleManager.setNewLocale(context,RU)
                }
                R.id.cr->{
                    LocaleManager.setNewLocale(context,EN)
                }
            }
            bottomSheetDialog.dismiss()
            activity.recreate()
        }
    }

    fun getString(id:Int):String{
        return activity.getString(id).lowercase()
    }
}