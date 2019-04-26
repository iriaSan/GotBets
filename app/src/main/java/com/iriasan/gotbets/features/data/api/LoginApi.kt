package com.iriasan.gotbets.features.data.api


import com.iriasan.gotbets.features.domain.models.LoginModelPost
import com.iriasan.gotbets.features.domain.models.UserModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

internal interface LoginApi {
    companion object {
        private const val USER = "/v2/users/login"
    }

    @POST(USER)
    fun login(@Body loginModelPost: LoginModelPost?): Call<UserModel>?


}
