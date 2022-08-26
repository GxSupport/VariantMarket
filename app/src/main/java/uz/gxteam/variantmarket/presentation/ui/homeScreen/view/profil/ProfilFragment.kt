package uz.gxteam.variantmarket.presentation.ui.homeScreen.view.profil

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import uz.gxteam.variantmarket.R
import uz.gxteam.variantmarket.databinding.FragmentProfilBinding
import uz.gxteam.variantmarket.presentation.ui.base.BaseFragment
import uz.gxteam.variantmarket.utils.extensions.enabled
import uz.gxteam.variantmarket.utils.extensions.enabledFalse
import uz.gxteam.variantmarket.utils.extensions.gone
import uz.gxteam.variantmarket.utils.extensions.visible

class ProfilFragment : BaseFragment<FragmentProfilBinding>() {
    override fun setup(savedInstanceState: Bundle?) {
      binding.apply {

      }
    }

    override fun start(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.edite_profile,menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.edite->{
                viewEnabled()
               appCompositionRoot.snackBarData(binding.root,requireActivity().getString(R.string.edit_profile)){
                   viewEnabledFalse()
               }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun viewEnabled(){
        binding.nameEdt.enabled()
        binding.emailEdt.enabled()
        binding.phoneNumberEdt.enabled()
        binding.locationEdt.enabled()
        binding.radioGroup.enabled()
        binding.editBtn.visible()
    }

    fun viewEnabledFalse(){
        binding.nameEdt.enabledFalse()
        binding.emailEdt.enabledFalse()
        binding.phoneNumberEdt.enabledFalse()
        binding.locationEdt.enabledFalse()
        binding.radioGroup.enabledFalse()
        binding.editBtn.gone()
    }
}