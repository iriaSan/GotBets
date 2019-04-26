package com.iriasan.gotbets.core.security

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Authenticator
@Inject constructor() {
    fun userLoggedIn(): Boolean {
        //here logic to check user logged
        return true
    }
}
