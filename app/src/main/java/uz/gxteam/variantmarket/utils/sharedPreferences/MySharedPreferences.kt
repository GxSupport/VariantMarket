package uz.gxteam.variantmarket.utils.sharedPreferences

import android.Manifest.permission_group.PHONE
import android.content.Context
import android.content.SharedPreferences
import android.provider.Telephony.Carriers.PASSWORD

import dagger.hilt.android.qualifiers.ApplicationContext
import uz.gxteam.variantmarket.utils.appConstant.AppConstant.ACCESS_TOKEN
import uz.gxteam.variantmarket.utils.appConstant.AppConstant.CODE_CONFIRM
import uz.gxteam.variantmarket.utils.appConstant.AppConstant.COMPANY_NAME
import uz.gxteam.variantmarket.utils.appConstant.AppConstant.EMPTY
import uz.gxteam.variantmarket.utils.appConstant.AppConstant.LANG
import uz.gxteam.variantmarket.utils.appConstant.AppConstant.REFRESH_TOKEN
import uz.gxteam.variantmarket.utils.appConstant.AppConstant.REGISTER_DATA
import uz.gxteam.variantmarket.utils.appConstant.AppConstant.REGISTER_DATA_V
import uz.gxteam.variantmarket.utils.appConstant.AppConstant.RU
import uz.gxteam.variantmarket.utils.appConstant.AppConstant.THEME
import uz.gxteam.variantmarket.utils.appConstant.AppConstant.TOKEN_TYPE
import javax.inject.Inject

class MySharedPreferences @Inject constructor(
    @ApplicationContext var context: Context
) {
    private val NAME = COMPANY_NAME
    private val MODE = Context.MODE_PRIVATE
    private val sharedPreferences:SharedPreferences = context.getSharedPreferences(NAME,MODE)

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }
    fun clearToken(){
        sharedPreferences.edit().remove(ACCESS_TOKEN).apply()
        sharedPreferences.edit().remove(REFRESH_TOKEN).apply()
        sharedPreferences.edit().remove(TOKEN_TYPE).apply()
        sharedPreferences.edit().remove(PHONE).apply()
        sharedPreferences.edit().remove(PASSWORD).apply()
    }

    fun clearAll() {
        sharedPreferences.edit().clear().apply()
    }

    // TODO: access_token
    var accessToken:String?
        get() = sharedPreferences.getString(ACCESS_TOKEN, EMPTY)
        set(value) = sharedPreferences.edit{
            if (value!=null) it.putString(ACCESS_TOKEN,value)
        }
    // TODO: refresh_token
    var refreshToken:String?
        get() = sharedPreferences.getString(REFRESH_TOKEN, EMPTY)
        set(value) = sharedPreferences.edit{
            if (value!=null) it.putString(REFRESH_TOKEN,value)
        }
    // TODO: Token_type
    var tokenType:String?
        get() = sharedPreferences.getString(TOKEN_TYPE, EMPTY)
        set(value) = sharedPreferences.edit{
            if (value!=null && value!="") it.putString(TOKEN_TYPE,value)
        }

    // TODO: Data Register
    var registerData:String?
        get() = sharedPreferences.getString(REGISTER_DATA, EMPTY)
        set(value) = sharedPreferences.edit{
            if (value!=null && value!="") it.putString(REGISTER_DATA,value)
        }

    // TODO: Code Confirm
    var codeConfirm:String?
        get() = sharedPreferences.getString(CODE_CONFIRM, EMPTY)
        set(value) = sharedPreferences.edit{
            if (value!=null && value!="") it.putString(CODE_CONFIRM,value)
        }
    // TODO: Myshared Register
    var registerDataV:Int?
        get() = sharedPreferences.getInt(REGISTER_DATA_V, 0)
        set(value) = sharedPreferences.edit{
            if (value!=null && value!=0) it.putInt(REGISTER_DATA_V,value)
        }

    // TODO: language
    var lang:String?
        get() = sharedPreferences.getString(LANG, RU)
        set(value) = sharedPreferences.edit{
            if (value!=null) it.putString(LANG,value)
        }
    // TODO: theme
    var theme:Boolean?
        get() = sharedPreferences.getBoolean(THEME,false)
        set(value) = sharedPreferences.edit{
            if (value!=null) it.putBoolean(THEME,value)
        }
}