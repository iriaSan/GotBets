package com.iriasan.gotbets.core.extension

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.Factory
import androidx.lifecycle.ViewModelProviders
import com.iriasan.gotbets.BaseActivity
import com.iriasan.gotbets.BaseFragment
import kotlinx.android.synthetic.main.activity_layout.*

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) =
    beginTransaction().func().commit()

inline fun <reified T : ViewModel> Fragment.viewModel(factory: Factory, body: T.() -> Unit): T {
    val vm = ViewModelProviders.of(this, factory)[T::class.java]
    vm.body()
    return vm
}

fun BaseFragment.close() = fragmentManager?.popBackStack()

val BaseFragment.viewContainer: View get() = (activity as BaseActivity).fragmentContainer

val BaseFragment.appContext: Context get() = activity?.applicationContext!!

fun Fragment.addChildFragment(fragment: Fragment, frameId: Int) =
    childFragmentManager.beginTransaction().replace(frameId, fragment).commit()

fun Fragment.changeStatusBarColor(activity: Activity, color: Int) {
    val window = activity.window
    window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window?.statusBarColor = ContextCompat.getColor(activity, color)
}

fun Fragment.hideKeyBoardFromFragment(activity: Activity) {
    val view = activity.currentFocus
    if (view != null) {
        val inputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

fun Fragment.closeActivitiesAndGoTo(klass: Class<*>) {
    // Use like --->
    // closeActivitiesAndGoTo(this.activity,SplashActivity::class.java)
    val intent = Intent(context, klass)
    val cn = intent.component
    val mainIntent = Intent.makeRestartActivityTask(cn)
    this.activity?.startActivity(mainIntent)
}