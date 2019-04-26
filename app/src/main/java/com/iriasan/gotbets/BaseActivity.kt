package com.iriasan.gotbets

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.iriasan.gotbets.core.di.ApplicationComponent
import com.iriasan.gotbets.core.extension.inTransaction
import kotlinx.android.synthetic.main.toolbar.*


/**
 * Base Activity class with helper methods for handling professionalFragment transactions and back button
 * events.
 *
 * @see AppCompatActivity
 */
abstract class BaseActivity : AppCompatActivity() {

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (this.application as AndroidApplication).appComponent
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        setContentView(R.layout.activity_layout)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        addFragment(savedInstanceState)
    }

    override fun onBackPressed() {
        (supportFragmentManager.findFragmentById(R.id.fragmentContainer) as BaseFragment).onBackPressed()
        super.onBackPressed()
    }

    internal fun addFragment(savedInstanceState: Bundle?) =
        savedInstanceState
            ?: supportFragmentManager.inTransaction { add(R.id.fragmentContainer, fragment()) }

    abstract fun fragment(): BaseFragment
}
