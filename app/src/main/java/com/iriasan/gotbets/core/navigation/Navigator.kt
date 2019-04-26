package com.iriasan.gotbets.core.navigation

import android.content.Context
import android.view.View
import com.iriasan.gotbets.core.security.Authenticator
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Navigator
@Inject constructor(private val authenticator: Authenticator?) {

    fun appInit(context: Context) {
        when (authenticator?.userLoggedIn()) {
//            true -> showLogin(context)
//            false -> showSplash(context)
        }
    }

//    private fun showSplash(context: Context) = context.startActivity(SplashActivity.callingIntent(context))
//
//    fun showLogin(context: Context) {
//        when (authenticator?.userLoggedIn()) {
//            true -> context.startActivity(LoginActivity.callingIntent(context))
//            false -> AuthManager().authExpiredAndGoLogin(context)
//        }
//    }
//
//    fun showMain(context: Context) {
//        when (authenticator?.userLoggedIn()) {
//            true -> context.startActivity(MainActivity.callingIntent(context))
//            false -> AuthManager().authExpiredAndGoLogin(context)
//        }
//    }


    class Extras(val transitionSharedElement: View)
}
