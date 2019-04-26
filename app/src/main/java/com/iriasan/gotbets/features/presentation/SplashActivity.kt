package com.iriasan.gotbets.features.presentation

import android.content.Context
import android.content.Intent
import com.iriasan.gotbets.BaseFullScreenActivity

class SplashActivity : BaseFullScreenActivity() {
    companion object {
        fun callingIntent(context: Context) = Intent(context, SplashActivity::class.java)
    }

    override fun fragment() = SplashFragment()
}
