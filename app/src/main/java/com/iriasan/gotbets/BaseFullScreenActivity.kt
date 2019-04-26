package com.iriasan.gotbets

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Base Activity class with helper methods for handling professionalFragment transactions and back button
 * events.
 *
 * @see AppCompatActivity
 */
abstract class BaseFullScreenActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullscreen_layout)
        setSupportActionBar(toolbar)
        addFragment(savedInstanceState)
    }
}
