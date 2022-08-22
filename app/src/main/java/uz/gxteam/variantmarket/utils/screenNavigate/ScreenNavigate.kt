package uz.gxteam.variantmarket.utils.screenNavigate

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
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