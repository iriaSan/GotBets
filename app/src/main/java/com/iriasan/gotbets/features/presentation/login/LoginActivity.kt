package com.iriasan.gotbets.features.presentation.login

import android.content.Context
import android.content.Intent
import com.iriasan.gotbets.BaseFragment
import com.iriasan.gotbets.BaseFullScreenActivity


/**
 * Created by Iria Sanchez on 26/04/2019.
 */
class LoginActivity : BaseFullScreenActivity() {
    override fun fragment(): BaseFragment = LoginFragment.newInstance()

    companion object {
        fun callingIntent(context: Context): Intent = Intent(context, LoginActivity::class.java)
    }
}