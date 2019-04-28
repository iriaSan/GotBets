package com.iriasan.gotbets.features.presentation.signup

import androidx.lifecycle.MutableLiveData
import com.iriasan.gotbets.core.exception.Failure
import com.iriasan.gotbets.core.extension.isEmailValid
import com.iriasan.gotbets.core.platform.BaseViewModel
import com.iriasan.gotbets.features.domain.interactor.SignupInteractor
import com.iriasan.gotbets.features.domain.models.SignupModelPost
import javax.inject.Inject


/**
 * Created by Iria Sanchez on 28/04/2019.
 */
class SignupViewModel @Inject constructor(private val signupInteractor: SignupInteractor) : BaseViewModel(){
    var signupDone: MutableLiveData<Boolean> = MutableLiveData()

    fun signup(signupModelPost: SignupModelPost){
        when{
            signupModelPost.email.isNullOrBlank() && signupModelPost.password.isNullOrBlank() &&
                    signupModelPost.phone.isNullOrBlank() && signupModelPost.name.isNullOrBlank() ->{
                handleFailure(Failure.EmptyField(Companion.ERRORTYPES.EMAIL))
                handleFailure(Failure.EmptyField(Companion.ERRORTYPES.PASSWORD))
                handleFailure(Failure.EmptyField(Companion.ERRORTYPES.PHONE))
                handleFailure(Failure.EmptyField(Companion.ERRORTYPES.NAME))

            }

            signupModelPost.email.isNullOrBlank() -> {
                handleFailure(Failure.EmptyField(Companion.ERRORTYPES.EMAIL))
            }

            !signupModelPost.email?.isEmailValid()!! -> {
                handleFailure(Failure.EmptyField(Companion.ERRORTYPES.WRONG_EMAIL))
            }

            signupModelPost.phone.isNullOrBlank() -> {
                handleFailure(Failure.EmptyField(Companion.ERRORTYPES.PHONE))
            }

            signupModelPost.name.isNullOrBlank() -> {
                handleFailure(Failure.EmptyField(Companion.ERRORTYPES.NAME))
            }

            signupModelPost.password.isNullOrBlank() -> {
                handleFailure(Failure.EmptyField(Companion.ERRORTYPES.PASSWORD))
            }

            else -> {
                signupModelPost.let { SignupInteractor.Params(it) }.let {
                    signupInteractor.execute({ it.either(::handleFailure, ::handleUser) }, it)
                }
            }

        }
    }

    private fun handleUser(done: Boolean) {
        this.signupDone.value = done
    }


    companion object {
        enum class ERRORTYPES { WRONG_EMAIL, EMAIL, PASSWORD, NAME, PHONE }
    }
}