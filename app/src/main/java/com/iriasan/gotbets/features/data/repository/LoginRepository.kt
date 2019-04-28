package com.iriasan.gotbets.features.data.repository


import com.iriasan.gotbets.core.exception.Failure
import com.iriasan.gotbets.core.functional.Either
import com.iriasan.gotbets.core.platform.NetworkHandler
import com.iriasan.gotbets.features.data.services.LoginService

import com.iriasan.gotbets.features.domain.models.LoginModelPost
import com.iriasan.gotbets.features.domain.models.SignupModelPost
import javax.inject.Inject

interface LoginRepository {
    fun login(loginModelPost: LoginModelPost?): Either<Failure, Boolean>
    fun signup(signupModelPost: SignupModelPost?): Either<Failure, Boolean>

    class Login
    @Inject constructor(private val networkHandler: NetworkHandler, private val loginService: LoginService) : LoginRepository {


        override fun signup(signupModelPost: SignupModelPost?): Either<Failure, Boolean> {
            return when (networkHandler.isConnected) {
                true -> request(loginService.signUp(signupModelPost))
                false -> Either.Left(Failure.NetworkConnection)
            }
        }

        override fun login(loginModelPost: LoginModelPost?): Either<Failure, Boolean> {
            return when (networkHandler.isConnected) {
                true -> request(loginService.saveLogin(loginModelPost))
                false -> Either.Left(Failure.NetworkConnection)
            }
        }


            private fun request(result: Either<Failure, Boolean>): Either<Failure, Boolean> {
                return try {
                    result
                } catch (e: Throwable) {
                    Either.Left(Failure.ServerError)
                }
            }

    }
}