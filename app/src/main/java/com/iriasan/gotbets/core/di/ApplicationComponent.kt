package com.iriasan.gotbets.core.di

import com.iriasan.gotbets.AndroidApplication
import com.iriasan.gotbets.BaseActivity
import com.iriasan.gotbets.BaseFragment
import com.iriasan.gotbets.core.di.viewmodel.ViewModelModule
import com.iriasan.gotbets.core.navigation.RouteActivity
import com.iriasan.gotbets.features.presentation.*
import com.iriasan.gotbets.features.presentation.login.LoginActivity
import com.iriasan.gotbets.features.presentation.login.LoginFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(application: AndroidApplication)
    fun inject(baseActivity: BaseActivity)
    fun inject(baseFragment: BaseFragment)
    fun inject(routeActivity: RouteActivity)
    fun inject(splashActivity: SplashActivity)
    fun inject(splashFragment: SplashFragment)
    fun inject(loginActivity: LoginActivity)
    fun inject(loginFragment: LoginFragment)
    fun inject(mainActivity: MainActivity)
    fun inject(mainFragment: MainFragment)
}
