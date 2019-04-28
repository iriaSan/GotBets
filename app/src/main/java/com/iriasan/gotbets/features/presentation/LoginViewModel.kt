package com.iriasan.gotbets.features.presentation

import androidx.lifecycle.MutableLiveData
import com.iriasan.gotbets.core.exception.Failure
import com.iriasan.gotbets.core.extension.isEmailValid
import com.iriasan.gotbets.core.platform.BaseViewModel
import com.iriasan.gotbets.features.domain.interactor.LoginInteractor
import com.iriasan.gotbets.features.domain.models.LoginModelPost
import javax.inject.Inject


/**
 * Created by Iria Sanchez on 26/04/2019.
 */
class LoginViewModel @Inject constructor(private val loginInteractor: LoginInteractor) : BaseViewModel() {
    var loginDone: MutableLiveData<Boolean> = MutableLiveData()
    var resetDone: MutableLiveData<Boolean> = MutableLiveData()

    fun login(loginModelPost: LoginModelPost) {
        when {
            loginModelPost.email.isNullOrBlank() && loginModelPost.password.isNullOrBlank() -> {
                handleFailure(Failure.EmptyField(ERRORTYPES.EMAIL))
                handleFailure(Failure.EmptyField(ERRORTYPES.PASSWORD))
            }

            loginModelPost.email.isNullOrBlank() -> {
                handleFailure(Failure.EmptyField(ERRORTYPES.EMAIL))
            }

            !loginModelPost.email?.isEmailValid()!! -> {
                handleFailure(Failure.EmptyField(ERRORTYPES.WRONG_EMAIL))
            }

            loginModelPost.password.isNullOrBlank() -> {
                handleFailure(Failure.EmptyField(ERRORTYPES.PASSWORD))
            }

            else -> {
                loginModelPost.let { LoginInteractor.Params(it) }.let { it ->
                    loginInteractor.execute({ it.either(::handleFailure, ::handleUser) }, it)
                }
            }
        }
    }


    private fun handleUser(done: Boolean) {
        this.loginDone.value = done
    }

    private fun handleResetPassword(isDone: Boolean) {
        this.resetDone.value = isDone
    }

    companion object {
        enum class ERRORTYPES { WRONG_EMAIL, EMAIL, PASSWORD, RESET_PASSWORD_EMPTY_EMAIL, RESET_PASSWORD_EMPTY_EMAIL_INVALID, RESET_PASSWORD_EMAIL_ERROR }
    }

}