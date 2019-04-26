package com.iriasan.gotbets

import android.app.Application
import android.content.Context
import android.util.Log
import com.iriasan.gotbets.core.di.ApplicationComponent
import com.iriasan.gotbets.core.di.ApplicationModule
import com.iriasan.gotbets.core.di.DaggerApplicationComponent


/**
 * Created by Iria Sanchez on 26/04/2019.
 */
class AndroidApplication : Application() {

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        this.injectMembers()
        instance = this

    }


    fun getContext(): Context? = instance.applicationContext
    private fun injectMembers() = appComponent.inject(this)


    companion object {
        lateinit var instance: AndroidApplication
            private set

        fun buildIsInProduction(): Boolean {
            Log.i("BuildConfig.FLAVOR-->", BuildConfig.FLAVOR)
            Log.i("BuildConfig.DEBUG", BuildConfig.DEBUG.toString())
            Log.i("buildIsInProduction-->", (BuildConfig.FLAVOR == "production" && !BuildConfig.DEBUG).toString())
            return BuildConfig.FLAVOR == "production" && !BuildConfig.DEBUG
        }
    }
}