package com.iriasan.gotbets.features.data.repository


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
    fun login(loginModelPost: LoginModelPost?): Either<Failure, UserModel>
    class Login
    @Inject constructor(private val networkHandler: NetworkHandler, private val service: LoginService) :
        LoginRepository {
        @Inject
        lateinit var userManager: UserManager

        override fun login(loginModelPost: LoginModelPost?): Either<Failure, UserModel> {
            return when (networkHandler.isConnected) {
                true -> request(service.login(loginModelPost)!!, { it -> it }, UserModel())
                false -> Either.Left(Failure.NetworkConnection)
            }
        }


        private fun <T, R> request(call: Call<T>, transform: (T) -> R, default: T): Either<Failure, R> {
            return try {
                val response = call.execute()
                when (response.isSuccessful) {
                    true -> {
                        val responseTransformed = transform((response.body() ?: default))
                        if (responseTransformed is UserModel) {
                            userManager.saveUserModel(responseTransformed)
                        }
                        Either.Right(transform((response.body() ?: default)))
                    }
                    false -> {
                        if (response.code() == 401) {
                            Either.Left(Failure.Unauthorized)
                        } else {
                            Either.Left(Failure.ServerError)
                        }
                    }
                }
            } catch (e: Throwable) {
                Either.Left(Failure.ServerError)
            }
        }


    }
}
