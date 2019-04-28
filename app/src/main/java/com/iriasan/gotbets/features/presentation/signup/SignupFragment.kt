package com.iriasan.gotbets.features.presentation.signup

import com.iriasan.gotbets.BaseFragment
import com.iriasan.gotbets.R


/**
 * Created by Iria Sanchez on 28/04/2019.
 */
class SignupFragment : BaseFragment() {


    override fun layoutId(): Int = R.layout.fragment_signup

    override fun showProgress() {
    }

    override fun hideProgress() {
    }

    companion object {
        fun newInstance(): SignupFragment {
            return SignupFragment()
        }
    }
}