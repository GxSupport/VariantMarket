package uz.gxteam.variantmarket.app

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.HiltAndroidApp
import uz.gxteam.variantmarket.utils.AppConstant
import uz.gxteam.variantmarket.utils.AppConstant.THEME
import uz.gxteam.variantmarket.utils.language.LocaleManager
import uz.gxteam.variantmarket.utils.sharedPreferences.MySharedPreferences
import javax.inject.Inject

@HiltAndroidApp
class App:Application(){

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LocaleManager.setLocale(base))
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        LocaleManager.setLocale(this)
    }
}