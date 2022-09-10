package uz.gxteam.variantmarket.presentation.ui.splashScreen

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.navigation.fragment.findNavController
import uz.gxteam.variantmarket.R
import uz.gxteam.variantmarket.databinding.FragmentSettingsBinding
import uz.gxteam.variantmarket.databinding.FragmentSplashBinding
import uz.gxteam.variantmarket.presentation.ui.base.BaseFragment

class SplashFragment : BaseFragment<FragmentSplashBinding>() {

    override fun setup(savedInstanceState: Bundle?) {
        (activity as AppCompatActivity).supportActionBar?.hide()
        binding.apply {
            motionLayout.startLayoutAnimation()
            motionLayout.setTransitionListener(object:MotionLayout.TransitionListener{
                override fun onTransitionStarted(
                    motionLayout: MotionLayout?,
                    startId: Int,
                    endId: Int
                ) {

                }

                override fun onTransitionChange(
                    motionLayout: MotionLayout?,
                    startId: Int,
                    endId: Int,
                    progress: Float
                ) {

                }

                override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                    Handler(Looper.getMainLooper()).postDelayed({
                        findNavController().navigate(R.id.action_splashFragment_to_authFragment)
//                        appCompositionRoot.activityApp.startNewActivity(MainActivity::class.java)
//                        appCompositionRoot.activityApp.finish()
                    },1200)
                }

                override fun onTransitionTrigger(
                    motionLayout: MotionLayout?,
                    triggerId: Int,
                    positive: Boolean,
                    progress: Float
                ) {

                }

            })
        }
    }

    override fun start(savedInstanceState: Bundle?) {

    }

    override fun inflateViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentSplashBinding =
        FragmentSplashBinding.inflate(inflater,container,false)
}