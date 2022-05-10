package com.sadikahmetozdemir.notezz.ui.splash

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.sadikahmetozdemir.notezz.R
import com.sadikahmetozdemir.notezz.base.BaseFragment
import com.sadikahmetozdemir.notezz.databinding.FragmentSplashBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment :
    BaseFragment<FragmentSplashBinding, SplashViewModel>(R.layout.fragment_splash) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch(Dispatchers.Main) {
            delay(3000)
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFolderFragment())

        }
    }

}