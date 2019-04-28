package com.iriasan.gotbets.features.presentation.login

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import com.iriasan.gotbets.BaseFragment
import com.iriasan.gotbets.R
import com.iriasan.gotbets.core.exception.Failure
import com.iriasan.gotbets.core.extension.failure
import com.iriasan.gotbets.core.extension.observe
import com.iriasan.gotbets.core.extension.viewModel
import com.iriasan.gotbets.core.navigation.Navigator
import com.iriasan.gotbets.features.domain.models.LoginModelPost
import kotlinx.android.synthetic.main.fragment_login.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import javax.inject.Inject


/**
 * Created by Iria Sanchez on 26/04/2019.
 */
class LoginFragment : BaseFragment(){

    @Inject
    lateinit var navigator: Navigator
    private lateinit var loginViewModel: LoginViewModel


    override fun layoutId(): Int = R.layout.fragment_login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        loginViewModel = viewModel(viewModelFactory) {
            observe(loginDone, ::renderDone)
           failure(failure,::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun renderDone(done: Boolean?) {
        hideProgress()
        if (done!!) {
            navigator.showBet(requireContext())
            activity?.finishAndRemoveTask()
        }
    }

    private fun handleFailure(failure: Failure?) {
        hideProgress()
        when (failure) {
            is Failure.EmptyField -> {}
        }
    }

    private fun renderFailure(@StringRes message: Int) {
        notify(getText(message).toString())
    }

    private fun setupViews() {
        buttonLogin.onClick {
            hideKeyboard()
            showProgress()
            loginViewModel.login(LoginModelPost(etEmailLogin.text.toString(), etPasswordLogin.text.toString()))
        }

        btnSignUpLogin.onClick {
            navigator.showSignup(requireContext())
        }
    }


    override fun showProgress() {
        progress_login_fragment.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress_login_fragment.visibility = View.GONE
    }

    companion object {
        fun newInstance(): LoginFragment {
            return LoginFragment()
        }
    }
}