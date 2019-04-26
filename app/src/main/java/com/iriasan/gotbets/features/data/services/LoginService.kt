package com.iriasan.gotbets.features.data.services

import com.iriasan.gotbets.features.data.api.LoginApi
import com.iriasan.gotbets.core.networking.RetrofitBuilder
import com.iriasan.gotbets.features.domain.models.LoginModelPost
import com.iriasan.gotbets.features.domain.models.UserModel
import retrofit2.Call
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginService
@Inject constructor(retrofitBuilder: RetrofitBuilder) : LoginApi {
    private val loginApi by lazy { retrofitBuilder.retrofit().create(LoginApi::class.java) }
    override fun login(loginModelPost: LoginModelPost?): Call<UserModel>? = loginApi.login(loginModelPost)
}
