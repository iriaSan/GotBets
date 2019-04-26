package com.iriasan.gotbets.features.presentation

import android.os.Bundle
import android.view.View
import com.iriasan.gotbets.BaseFragment
import com.iriasan.gotbets.R
import com.iriasan.gotbets.core.navigation.Navigator
import javax.inject.Inject


/**
 * Created by Iria Sanchez on 26/04/2019.
 */
class MainFragment : BaseFragment() {
    @Inject
    lateinit var navigator: Navigator

    override fun layoutId() = R.layout.fragment_splash
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    override fun showProgress() {}

    override fun hideProgress() {}

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }
}