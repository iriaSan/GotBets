package com.iriasan.gotbets.features.presentation.signup

import android.content.Context
import android.content.Intent
import com.iriasan.gotbets.BaseActivity
import com.iriasan.gotbets.BaseFragment


/**
 * Created by Iria Sanchez on 28/04/2019.
 */
class SignupActivity : BaseActivity() {
    override fun fragment(): BaseFragment = SignupFragment.newInstance()

    companion object {
        fun callingIntent(context: Context): Intent = Intent(context, SignupActivity::class.java)
    }
}