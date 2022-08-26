package uz.gxteam.variantmarket.utils.sharedPreferences

import android.content.Context
import android.content.SharedPreferences

import dagger.hilt.android.qualifiers.ApplicationContext
import uz.gxteam.variantmarket.utils.AppConstant.ACCESS_TOKEN
import uz.gxteam.variantmarket.utils.AppConstant.COMPANY_NAME
import uz.gxteam.variantmarket.utils.AppConstant.EMPTY
import uz.gxteam.variantmarket.utils.AppConstant.REFRESH_TOKEN
import uz.gxteam.variantmarket.utils.AppConstant.TOKEN_TYPE
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
}