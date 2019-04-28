package com.iriasan.gotbets.core.navigation

import android.content.Context
import android.view.View
import com.iriasan.gotbets.features.presentation.MainActivity
import com.iriasan.gotbets.core.security.AuthManager
import com.iriasan.gotbets.core.security.Authenticator
import com.iriasan.gotbets.features.presentation.login.LoginActivity
import com.iriasan.gotbets.features.presentation.SplashActivity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Navigator
@Inject constructor(private val authenticator: Authenticator?) {

    fun appInit(context: Context) {
        when (authenticator?.userLoggedIn()) {
            true -> showLogin(context)
            false -> showSplash(context)
        }
    }

    private fun showSplash(context: Context) = context.startActivity(SplashActivity.callingIntent(context))

    fun showLogin(context: Context) {
        context.startActivity(LoginActivity.callingIntent(context))
    }

    fun showMain(context: Context) {
        when (authenticator?.userLoggedIn()) {
            true -> context.startActivity(MainActivity.callingIntent(context))
            false -> AuthManager().authExpiredAndGoLogin(context)
        }
    }


    class Extras(val transitionSharedElement: View)
}
