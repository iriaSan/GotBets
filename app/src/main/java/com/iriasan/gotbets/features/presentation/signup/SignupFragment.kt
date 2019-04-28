package com.iriasan.gotbets.features.presentation.signup

import android.os.Bundle
import android.view.View
import com.iriasan.gotbets.BaseFragment
import com.iriasan.gotbets.R
import com.iriasan.gotbets.core.exception.Failure
import com.iriasan.gotbets.core.extension.failure
import com.iriasan.gotbets.core.extension.observe
import com.iriasan.gotbets.core.extension.viewModel
import com.iriasan.gotbets.core.navigation.Navigator
import com.iriasan.gotbets.features.domain.models.SignupModelPost
import kotlinx.android.synthetic.main.fragment_signup.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import javax.inject.Inject


/**
 * Created by Iria Sanchez on 28/04/2019.
 */
class SignupFragment : BaseFragment() {

    @Inject
    lateinit var navigator: Navigator
    private lateinit var signupViewModel: SignupViewModel


    override fun layoutId(): Int = R.layout.fragment_signup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        signupViewModel = viewModel(viewModelFactory){
            observe(signupDone, ::renderDone)
          failure(failure,::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }


    private fun setupViews() {
        buttonSignUp.onClick {
            hideKeyboard()
            showProgress()
            signupViewModel.signup(SignupModelPost(etEmailSignup.text.toString(),
                etPhoneSignup.text.toString(), etNameSignup.text.toString(),
                etPasswordSignup.text.toString()))
        }
    }

    override fun showProgress() {
        progress_signup_fragment.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress_signup_fragment.visibility = View.GONE
    }

    private fun renderDone(done: Boolean?) {
        hideProgress()
        if (done!!) {
            navigator.showMain(requireContext())
            activity?.finishAndRemoveTask()
        }
    }

    private fun handleFailure(failure: Failure?) {
        hideProgress()
        when (failure) {
            is Failure.EmptyField -> {}
        }
    }

    companion object {
        fun newInstance(): SignupFragment {
            return SignupFragment()
        }
    }
}