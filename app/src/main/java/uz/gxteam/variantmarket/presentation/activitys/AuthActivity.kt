package uz.gxteam.variantmarket.presentation.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import uz.gxteam.variantmarket.R
import uz.gxteam.variantmarket.databinding.ActivityAuthBinding
import uz.gxteam.variantmarket.utils.composition.AppCompositionRoot
import uz.gxteam.variantmarket.utils.uiController.UiController
import uz.gxteam.variantmarket.viewModels.mainViewModel.MainViewModel
@AndroidEntryPoint
class AuthActivity : AppCompatActivity(),UiController {
    private lateinit var binding:ActivityAuthBinding
    lateinit var appCompositionRoot: AppCompositionRoot
    private val mainViewModel:MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navControllerApp = supportFragmentManager.findFragmentById(R.id.fragment_auth) as NavHostFragment
        appCompositionRoot = AppCompositionRoot(this,navControllerApp.navController,this,this,mainViewModel)
    }

    override fun onNavigateUp(): Boolean {
        return findNavController(R.id.fragment_auth).navigateUp()
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun error(errorCode: Int, message: String) {

    }
}