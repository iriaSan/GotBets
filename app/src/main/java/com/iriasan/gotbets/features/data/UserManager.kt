package com.iriasan.gotbets.features.data

import com.iriasan.gotbets.core.persistence.SharedPreferencesHelper
import com.iriasan.gotbets.features.domain.models.UserModel
import javax.inject.Inject

class UserManager @Inject constructor() {
    fun load() = SharedPreferencesHelper().loadUserModel(SharedPreferencesHelper().KEY_USER_MODEL) as? UserModel

    fun saveUserModel(value: UserModel) =
        SharedPreferencesHelper().save(SharedPreferencesHelper().KEY_USER_MODEL, value)

//    fun updateUserModel(value: UserModel) {
//        val mergedUserModel = UserModel(value.accessToken, value.refreshToken, load()?.userData)
//        SharedPreferencesHelper().save(SharedPreferencesHelper().KEY_USER_MODEL, mergedUserModel)
//        value.userData?.id.let { Crashlytics.setUserIdentifier(it) }
//    }
//
//    /* To save only the updated userData, no the complete UserModel that includes Auth, with this we will keep the auth data*/
//    fun saveUserData(value: UserData) {
//        val mergedUserModel = UserModel(load()?.accessToken, load()?.refreshToken, value)
//        SharedPreferencesHelper().save(SharedPreferencesHelper().KEY_USER_MODEL, mergedUserModel)
//    }
}