package com.iriasan.gotbets.core.di

import android.content.Context
import com.iriasan.gotbets.AndroidApplication
import com.iriasan.gotbets.core.networking.RetrofitBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: AndroidApplication) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Context = application


    @Provides
    @Singleton
    fun provideRetrofit() = RetrofitBuilder()

//    @Provides
//    @Singleton
//    fun provideAnalytics() = FirebaseAnalytics.getInstance(application.getContext()!!)

//    @Provides
//    @Singleton
//    fun provideLoginRepository(loginRepository: LoginRepository.Login): LoginRepository = loginRepository
}
