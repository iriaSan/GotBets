package com.iriasan.gotbets.features.presentation.bet

import android.content.Context
import android.content.Intent
import com.iriasan.gotbets.BaseActivity
import com.iriasan.gotbets.BaseFragment


/**
 * Created by Iria Sanchez on 28/04/2019.
 */
class BetActivity : BaseActivity() {

    companion object {
        fun callingIntent(context: Context) = Intent(context, BetActivity::class.java)
    }

    override fun fragment(): BaseFragment = BetFragment.newInstance()
}