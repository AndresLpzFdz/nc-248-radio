package com.nc248radio.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.nc248radio.databinding.FragmentSplashBinding
import com.nc248radio.utils.extensions.navigateSafely
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment: Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    //Lifecycle

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        waitDelay()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //Methods

    private fun waitDelay() {
        Handler(Looper.getMainLooper()).postDelayed({
            goMain()
        }, 1000)
    }

    //Navigation

    private fun goMain() =
        findNavController().navigateSafely(SplashFragmentDirections.goMain())


}