package uz.gxteam.variantmarket.presentation.activitys


import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import uz.gxteam.variantmarket.R
import uz.gxteam.variantmarket.databinding.ActivityMainBinding
import uz.gxteam.variantmarket.utils.composition.AppCompositionRoot
import uz.gxteam.variantmarket.utils.language.LocaleManager
import uz.gxteam.variantmarket.utils.uiController.UiController
import uz.gxteam.variantmarket.viewModels.mainViewModel.MainViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity(),UiController {
    lateinit var binding:ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val mainViewModel:MainViewModel by viewModels()
    lateinit var appCompositionRoot:AppCompositionRoot
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarMain.toolbar)

        val navControllerApp = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
        appCompositionRoot = AppCompositionRoot(this,navControllerApp.navController,this,this,mainViewModel)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.statusBarColor = resources.getColor(R.color.white)
        window.navigationBarColor = resources.getColor(R.color.white)
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.nav_home,R.id.category,R.id.orders,
                R.id.bankCard,R.id.favorites,R.id.questions,
                R.id.terms,R.id.settings), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        navController.addOnDestinationChangedListener { controller, destination, arguments ->
           when(controller.currentDestination?.id){
               R.id.nav_home->{
                   supportActionBar?.show()
                   supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_main_menu)
               }
               R.id.category->{
                   supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_main_menu)
               }
               R.id.orders->{
                   supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_main_menu)
               }
               R.id.bankCard->{
                   supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_main_menu)
               }
               R.id.favorites->{
                   supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_main_menu)
               }
               R.id.questions->{
                   supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_main_menu)
               }
               R.id.terms->{
                   supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_main_menu)
               }
               R.id.settings->{
                   supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_main_menu)
               }
               R.id.searchedDataFragment->{
                   supportActionBar?.hide()
               }
               R.id.dataProductFragment->{
                   supportActionBar?.hide()
               }
               R.id.categoryDataFragment->{
                   supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_left)
               }
           }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun showLoading() {
        appCompositionRoot.loadingDialog(true)
    }

    override fun hideLoading() {
        appCompositionRoot.loadingDialog(false)
    }

    override fun error(errorCode: Int, message: String,onClick:(idsClick:Boolean)->Unit) {
        appCompositionRoot.errorDialog(errorCode,message,onClick)
    }

    fun toolbarTitle(title:String){
        binding.appBarMain.toolbar.title = title
    }


    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocaleManager.setLocale(newBase));
    }


}