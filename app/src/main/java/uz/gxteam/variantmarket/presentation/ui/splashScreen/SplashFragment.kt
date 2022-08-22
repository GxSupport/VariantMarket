package uz.gxteam.variantmarket.presentation.ui.splashScreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import uz.gxteam.variantmarket.databinding.FragmentSplashBinding
import uz.gxteam.variantmarket.presentation.activitys.MainActivity
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
                        startActivity(Intent(requireContext(), MainActivity::class.java))
                        requireActivity().finish()
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
}