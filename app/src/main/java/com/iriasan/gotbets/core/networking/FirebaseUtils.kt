package com.iriasan.gotbets.core.networking

import com.google.firebase.auth.FirebaseAuth

open class FirebaseUtils {
     val firebaseUid: String?
        get() = FirebaseAuth.getInstance()?.currentUser?.uid
}