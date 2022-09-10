package uz.gxteam.variantmarket.utils.screenNavigate

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import uz.gxteam.variantmarket.R
import uz.gxteam.variantmarket.models.register.reqRegister.ReqRegister
import uz.gxteam.variantmarket.models.register.resRegister.ResponseRegisterData
import uz.gxteam.variantmarket.utils.AppConstant.REQUEST_REGISTER
import uz.gxteam.variantmarket.utils.AppConstant.RESPONSE_REGISTER_DATA

class ScreenNavigate(
    private val navController: NavController
) {


    fun createAuthPage(){
        navController.navigate(R.id.action_splashFragment_to_authFragment)
    }

    fun careateCategory(){
        navController.navigate(R.id.category)
    }


    fun createSearchDataViewInSearchView(search:String){
        var bundle = Bundle()
        bundle.putString("name",search)
        navController.navigate(R.id.action_searchFragment_to_searchedDataFragment,bundle,animationViewCreateRight())
    }

    fun createDataProductInFavorites(){
        navController.navigate(R.id.dataProductFragment,
            Bundle(),animationViewCreateRight())
    }

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
        navController.navigate(R.id.categoryDataFragment,bundle,animationViewCreateRight())
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

    fun createSaveCardUser(){
        navController.navigate(R.id.action_bankCard_to_createCardFragment,Bundle(),animationViewCreateRight())
    }


    fun createRegistrationPage(){
        navController.navigate(R.id.action_authFragment_to_registrationFragment, Bundle(),animationViewCreateRight())
    }

    fun createConfirmPage(
        responseRegisterData: ResponseRegisterData,
        reqRegister: ReqRegister){
        val bundle = Bundle()
        bundle.putSerializable(RESPONSE_REGISTER_DATA,responseRegisterData)
        bundle.putSerializable(REQUEST_REGISTER,reqRegister)
        navController.navigate(R.id.action_registrationFragment_to_confirmUserFragment, bundle,animationViewCreateRight())
    }
    fun popBackStack(){
        navController.popBackStack()
    }

    fun createNewPasswordPage(){
        navController.navigate(R.id.action_authFragment_to_passwordResetFragment,Bundle(),animationViewCreateRight())
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