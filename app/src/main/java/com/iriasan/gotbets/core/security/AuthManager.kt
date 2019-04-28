package com.iriasan.gotbets.core.security

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.core.app.ActivityCompat.finishAffinity
import com.iriasan.gotbets.core.persistence.SharedPreferencesHelper
import com.iriasan.gotbets.features.presentation.SplashActivity
import org.jetbrains.anko.doAsync

class AuthManager {

    fun authExpiredAndGoLogin(context: Context?) {
        doAsync {
            context?.let { SharedPreferencesHelper().clear(it) }
  closeActivitiesGoLogin(context)
            // TODO: remove all persistence
        }
    }

    fun closeAppDeleteUserModel(context: Context, activity: Activity) {
        doAsync {
            context.let { SharedPreferencesHelper().clearUserModel(it, ::closeApp, activity) }
        }
    }

    private fun closeApp(activity: Activity) {
        finishAffinity(activity)
        System.exit(0)
    }
//
    private fun closeActivitiesGoLogin(context: Context?) {
        val intent = Intent(context, SplashActivity::class.java)
        val cn = intent.component
        val mainIntent = Intent.makeRestartActivityTask(cn)
        context?.startActivity(mainIntent)
    }
}
