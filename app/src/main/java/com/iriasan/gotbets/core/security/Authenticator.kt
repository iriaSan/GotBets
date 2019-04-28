package com.iriasan.gotbets.core.security

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Authenticator
@Inject constructor() {
    fun userLoggedIn(): Boolean {
        //here logic to check user logged
        FirebaseAuth.getInstance().currentUser.let { itUser ->
            return itUser?.uid != null
        }
    }
}
