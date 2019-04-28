package com.iriasan.gotbets.features.data.services

import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.iriasan.gotbets.core.exception.Failure
import com.iriasan.gotbets.core.functional.Either
import com.iriasan.gotbets.core.networking.FirebaseUtils
import com.iriasan.gotbets.features.domain.models.LoginModelPost
import com.iriasan.gotbets.features.domain.models.SignupModelPost
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginService @Inject constructor(){
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    fun saveLogin(loginModelPost: LoginModelPost?): Either<Failure, Boolean> {
        return try {
            val task: Task<AuthResult> = loginModelPost?.email?.let { itEmail ->
                loginModelPost.password?.let { itPassword ->
                    auth.signInWithEmailAndPassword(itEmail, itPassword)
                }
            }!!

            Tasks.await(task).let {
                when {
                    it.user != null -> Either.Right(true)
                    else -> Either.Right(false)
                }
            }
        } catch (e: Throwable) {
            Either.Right(false)
        }
    }

    fun signUp(signupModelPost: SignupModelPost?): Either<Failure, Boolean> {
        return try {
            val task: Task<AuthResult> = signupModelPost?.email?.let { itEmail ->
                signupModelPost.password?.let { itPassword ->
                    auth.createUserWithEmailAndPassword(itEmail, itPassword)
                }
            }!!

            Tasks.await(task).let {
                when {
                    it != null -> {saveUser(signupModelPost); Either.Right(true)}
                    else -> Either.Right(false)
                }
            }
        } catch (e: Throwable) {
            Either.Right(false)
        }
    }


    private fun saveUser(signupModelPost: SignupModelPost?): Boolean {
        return try {
            val userId = FirebaseUtils().firebaseUid
            val userRef = userId?.let { db.collection("Users").document(it) }
            Tasks.await(signupModelPost?.let { userRef?.set(it) }!!) != null
        } catch (e: Throwable) {
           false
        }
    }




}
