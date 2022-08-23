package uz.gxteam.variantmarket.utils.screenNavigate

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import uz.gxteam.variantmarket.R

class ScreenNavigate(
    private val navController: NavController
) {


    fun createDataProduct(){
        navController.navigate(R.id.action_searchedDataFragment_to_dataProductFragment,
            Bundle(),animationViewCreatebottom())
    }

    fun createContainerProduct(name:String,position:Int){
        var bundle = Bundle()
        bundle.putString("name",name)
        bundle.putInt("pos",position)
        navController.navigate(R.id.action_nav_home_to_searchedDataFragment,bundle,animationViewCreateRight())
    }


    fun createDataProductFragment(name:String,position:Int){
        var bundle = Bundle()
        bundle.putString("name",name)
        bundle.putInt("pos",position)
        navController.navigate(R.id.action_nav_home_to_categoryDataFragment,bundle,animationViewCreateRight())
    }
    fun createDataProductFragmentInCategoryView(){
        var bundle = Bundle()
        navController.navigate(R.id.action_categoryDataFragment_to_dataProductFragment,bundle,animationViewCreatebottom())
    }

    fun createFilterView(){
        navController.navigate(R.id.action_searchedDataFragment_to_filterViewFragment,Bundle(),animationViewCreatebottom())
    }
    fun createProdInfo(){
        navController.navigate(R.id.action_dataProductFragment_to_productInfoFragment,Bundle(),animationViewCreateRight())
    }

    fun createDataProdInfo(){
        navController.navigate(R.id.action_nav_home_to_dataProductFragment,Bundle(),animationViewCreateRight())
    }

    fun popBackStack(){
        navController.popBackStack()
    }


    fun createSearchDataView(){
        navController.navigate(R.id.searchFragment, Bundle(),animationViewCreateRight())
    }

    fun createCommentView(){
        navController.navigate(R.id.action_dataProductFragment_to_commentFragment,Bundle(),animationViewCreateRight())
    }

    fun animationViewCreateRight():NavOptions{
        return NavOptions.Builder()
            .setEnterAnim(R.anim.enter)
            .setExitAnim(R.anim.exit)
            .setPopEnterAnim(R.anim.pop_enter)
            .setPopExitAnim(R.anim.pop_exit)
            .build()
    }
    fun animationViewCreatebottom():NavOptions{
        return NavOptions.Builder()
            .setEnterAnim(R.anim.animation_in)
            .setExitAnim(R.anim.animation_out)
            .setPopExitAnim(R.anim.animation_out)
            .setPopEnterAnim(R.anim.animation_in)
            .build()
    }

}