package com.iriasan.gotbets.features.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.iriasan.gotbets.BaseActivity
import com.iriasan.gotbets.BaseFragment
import com.iriasan.gotbets.R

class MainActivity : BaseActivity() {

    override fun fragment(): BaseFragment = MainFragment.newInstance()

    companion object {
        fun callingIntent(context: Context): Intent =
            Intent(context, MainActivity::class.java)
    }
}
