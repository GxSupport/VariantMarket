package uz.gxteam.variantmarket.presentation.activitys


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import uz.gxteam.variantmarket.R
import uz.gxteam.variantmarket.databinding.ActivityMainBinding
import uz.gxteam.variantmarket.utils.composition.AppCompositionRoot
import uz.gxteam.variantmarket.utils.uiController.UiController


class MainActivity : AppCompatActivity(),UiController {
    private lateinit var binding:ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var appCompositionRoot:AppCompositionRoot
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarMain.toolbar)
        val navControllerApp = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
        appCompositionRoot = AppCompositionRoot(this,navControllerApp.navController,this,this)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.statusBarColor = resources.getColor(R.color.white)
        window.navigationBarColor = resources.getColor(R.color.white)
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(R.id.nav_home,R.id.nav_gallery), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        navController.addOnDestinationChangedListener { controller, destination, arguments ->
           when(controller.currentDestination?.id){
               R.id.nav_home->{
                   supportActionBar?.show()
                   supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_main_menu)
               }
               R.id.searchFragment->{
                   supportActionBar?.hide()
               }
               R.id.searchedDataFragment->{
                   supportActionBar?.hide()
               }
               R.id.dataProductFragment->{
                   supportActionBar?.hide()
               }
           }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun error(errorCode: Int, message: String) {

    }



}