package com.iriasan.gotbets.features.data.repository


import com.google.firebase.auth.FirebaseAuth
import com.iriasan.gotbets.core.exception.Failure
import com.iriasan.gotbets.core.functional.Either
import com.iriasan.gotbets.core.platform.NetworkHandler
import com.iriasan.gotbets.features.data.services.LoginService

import com.iriasan.gotbets.features.data.UserManager
import com.iriasan.gotbets.features.domain.models.LoginModelPost
import com.iriasan.gotbets.features.domain.models.UserModel
import retrofit2.Call
import javax.inject.Inject

interface LoginRepository {
    fun login(loginModelPost: LoginModelPost?): Either<Failure, Boolean>?

    class Login
    @Inject constructor(private val networkHandler: NetworkHandler) : LoginRepository {
        override fun login(loginModelPost: LoginModelPost?): Either<Failure, Boolean>? {
            return when (networkHandler.isConnected) {
                true -> request(loginModelPost)
                false -> Either.Left(Failure.NetworkConnection)
            }
        }

        private fun request(loginModelPost: LoginModelPost?): Either<Failure, Boolean> {
            return try {
                val response = loginModelPost?.email?.let {itEmail->
                    loginModelPost?.password?.let {itPassword->
                        FirebaseAuth.getInstance()
                            .signInWithEmailAndPassword(itEmail, itPassword)
                    }
                }
                response?.addOnCompleteListener { }.let { itResponse ->
                    when {
                        itResponse?.isSuccessful!! -> Either.Right(true)
                        else -> Either.Left(Failure.Unauthorized)
                    }
                }
            } catch (e: Throwable) {
                Either.Left(Failure.ServerError)
            }
        }
    }
}