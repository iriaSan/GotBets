package com.iriasan.gotbets.core.networking

import com.iriasan.gotbets.AndroidApplication
import com.iriasan.gotbets.core.security.AuthManager
import com.iriasan.gotbets.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RetrofitBuilder {

    fun retrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(createClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun createClient(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        okHttpClientBuilder.connectTimeout(5, TimeUnit.MINUTES)
        okHttpClientBuilder.readTimeout(5, TimeUnit.MINUTES)
        if (BuildConfig.DEBUG) {
           // val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
           // okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }

        val headersInterceptor = Interceptor { chain ->
            var request = chain.request()
            val language = Locale.getDefault().language
            val headerAuth = request.headers().newBuilder()
                .add("deviceType", "2")
                .add("appVersion", BuildConfig.VERSION_NAME)
                .add("language", language).build()
            request = request.newBuilder().headers(headerAuth).build()
            chain.proceed(request)
        }
        okHttpClientBuilder.addInterceptor(headersInterceptor)
        return okHttpClientBuilder.build()
    }

    fun retrofitAuth(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(createClientAuth())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun createClientAuth(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
           // val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            //okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }
        okHttpClientBuilder.addInterceptor(AuthenticationInterceptorRefreshToken(this))
        return okHttpClientBuilder.build()
    }
}

class AuthenticationInterceptorRefreshToken @Inject constructor(private val retrofitBuilder: RetrofitBuilder) :
    Interceptor {
//    var userManager = UserManager()

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val language = Locale.getDefault().language
        val authenticationRequest = originalRequest.newBuilder()
           // .addHeader("Authorization", "Bearer ${userManager.load()?.accessToken}")
            .addHeader("appVersion", BuildConfig.VERSION_NAME)
            .addHeader("deviceType", "2")
            .addHeader("language", language).build()
        val initialResponse = chain.proceed(authenticationRequest)
        if (initialResponse.code() == 403 || initialResponse.code() == 401 || initialResponse.code() == 404) {
            //val responseNewTokenLoginModel = requestNewToken(userManager.load()?.refreshToken)

            // FOR NOW WITHOUT REFRESHTOKEN
            // /////DELETE//////////////
            AuthManager().authExpiredAndGoLogin(AndroidApplication().getContext())

            return initialResponse
            // ////////////////////////
//            return if (responseNewTokenLoginModel==null || responseNewTokenLoginModel.code() != 200 || responseNewTokenLoginModel.code() != 204) {
//                AuthManager().authExpiredAndGoLogin(AndroidApplication().getContext())
//                initialResponse
//            } else {
//                // repeat initial request with the new  token
//                val newAuthenticationRequest = originalRequest.newBuilder()
//                        .addHeader("Authorization", "Bearer " + responseNewTokenLoginModel?.body())
//                        .build()
//                chain.proceed(newAuthenticationRequest)
//            }
        } else {
            return initialResponse
        }
    }

    //  private fun requestNewToken(refreshToken: String?): Response<TokenModel>? {
    // start a new synchronous network call
    //     val apiService = retrofitBuilder.retrofitAuth().create(ApiRefreshToken::class.java)
    // call for new token
//        apiService.refreshToken(refreshToken).execute().let {            Log.i("NEW TOKEN ---- >", it.body().toString())
//        }
    //   val responseNewTokenLoginModel = apiService.refreshToken(refreshToken).execute()

    //responseNewTokenLoginModel.

//        apiService.refreshToken(refreshToken).enqueue(object : Callback {
//
//        })


//        apiService.refreshToken(refreshToken).enqueue(object : Callback<TokenModel> {
//            override fun onResponse(call: Call<TokenModel>?, response: Response){
//                when {
//                    response != null && response.isSuccessful -> {
//                        Log.i("T O K E N --", response.body().toString())
//                    }
//                    else -> {
//                        Log.i("T O K E N --", response?.code().toString())
//                    }
//                }
//
//
//            }
//            override fun onFailure(call: Call<TokenModel>?, t: Throwable?) {
//                Log.i("T O K E N --", "ERROR")
//            }
//        })
//
//
//
//
//
//
//        if (responseNewTokenLoginModel !== null && responseNewTokenLoginModel.code() == 200) {
//            Log.i("NEW TOKEN ---- >", responseNewTokenLoginModel.body().toString())
//            //saveTokenLoginModel(responseNewTokenLoginModel.body())
//
//        }else {
//            return null
//        }
//        return responseNewTokenLoginModel
    // }
}

