package uz.gxteam.variantmarket.presentation.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import uz.gxteam.variantmarket.R
import uz.gxteam.variantmarket.databinding.ActivityAuthBinding
import uz.gxteam.variantmarket.utils.composition.AppCompositionRoot
import uz.gxteam.variantmarket.utils.extensions.isNotNullOrEmpty
import uz.gxteam.variantmarket.utils.extensions.startNewActivity
import uz.gxteam.variantmarket.utils.uiController.UiController
import uz.gxteam.variantmarket.viewModels.mainViewModel.MainViewModel
@AndroidEntryPoint
class AuthActivity : AppCompatActivity(),UiController {
    private lateinit var binding:ActivityAuthBinding
    lateinit var appCompositionRoot: AppCompositionRoot
    private val mainViewModel:MainViewModel by viewModels()
    private val myShared get() = mainViewModel.myShared
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navControllerApp = supportFragmentManager.findFragmentById(R.id.fragment_auth) as NavHostFragment
        appCompositionRoot = AppCompositionRoot(this,navControllerApp.navController,this,this,mainViewModel)

        if (myShared.accessToken?.isNotNullOrEmpty() == true && myShared.codeConfirm?.isNotNullOrEmpty() == true){
            startNewActivity(MainActivity::class.java)
            finish()
        }


    }

    override fun onNavigateUp(): Boolean {
        return findNavController(R.id.fragment_auth).navigateUp()
    }

    override fun showLoading() {
        appCompositionRoot.loadingDialog(true)
    }

    override fun hideLoading() {
        appCompositionRoot.loadingDialog(false)
    }

    override fun error(errorCode: Int, message: String,onClick:(isClick:Boolean)->Unit) {
        appCompositionRoot.errorDialog(errorCode,message,onClick)
    }

    override fun onBackPressed() {
        when(findNavController(R.id.fragment_auth).currentDestination?.id){
            R.id.authFragment->{
                finish()
            }
            else->{
                appCompositionRoot.screenNavigate.popBackStack()
            }
        }
    }
}