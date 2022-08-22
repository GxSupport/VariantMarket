package uz.gxteam.variantmarket.utils

import android.content.Context
import android.content.SharedPreferences
import uz.gxteam.variantmarket.utils.AppConstant.ACCESSTOKEN
import uz.gxteam.variantmarket.utils.AppConstant.COMPANY_NAME
import uz.gxteam.variantmarket.utils.AppConstant.EMPTYTEXT
import uz.gxteam.variantmarket.utils.AppConstant.PASSWORDAPP
import uz.gxteam.variantmarket.utils.AppConstant.REFRESHTOKEN
import uz.gxteam.variantmarket.utils.AppConstant.TOKENTYPE

class Preference(var context:Context) {
    private val NAME = COMPANY_NAME
    private val MODE = Context.MODE_PRIVATE
    private val preferences:SharedPreferences = context.getSharedPreferences(NAME,MODE)

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }


    /** passwordApp **/
    var passwordApp: String?
        get() = preferences.getString(PASSWORDAPP, EMPTYTEXT)
        set(value) = preferences.edit {
            if (value != null) {
                it.putString(PASSWORDAPP, value)
            }
        }
    /** passwordApp **/

    /** save accessToken **/
    var accessToken: String?
        get() = preferences.getString(ACCESSTOKEN, EMPTYTEXT)
        set(value) = preferences.edit {
            if (value != null) {
                it.putString(ACCESSTOKEN, value)
            }
        }
    /** save refreshToken **/
    var refreshToken: String?
        get() = preferences.getString(REFRESHTOKEN, EMPTYTEXT)
        set(value) = preferences.edit {
            if (value != null) {
                it.putString(REFRESHTOKEN, value)
            }
        }
    /** save tokenType **/
    var tokenType: String?
        get() = preferences.getString(TOKENTYPE, EMPTYTEXT)
        set(value) = preferences.edit {
            if (value != null) {
                it.putString(TOKENTYPE, value)
            }
        }

}