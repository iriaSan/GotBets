package com.iriasan.gotbets.features.presentation.splash

import android.os.Bundle
import android.view.View
import com.iriasan.gotbets.BaseFragment
import com.iriasan.gotbets.R
import com.iriasan.gotbets.core.navigation.Navigator
import org.jetbrains.anko.doAsync
import java.lang.Thread.sleep
import javax.inject.Inject

class SplashFragment : BaseFragment() {
    @Inject
    lateinit var navigator: Navigator

    override fun layoutId() = R.layout.fragment_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        goToWelcomeActivity()
    }

    private fun goToWelcomeActivity() {
        doAsync {
            sleep(1500)
            navigator.showLogin(requireContext())
        }
    }

    override fun showProgress() {}

    override fun hideProgress() {}
}