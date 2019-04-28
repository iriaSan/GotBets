package com.iriasan.gotbets.features.domain.interactor

import com.iriasan.gotbets.core.interactor.UseCase
import com.iriasan.gotbets.features.data.repository.LoginRepository
import com.iriasan.gotbets.features.domain.models.SignupModelPost
import javax.inject.Inject


/**
 * Created by Iria Sanchez on 28/04/2019.
 */
class SignupInteractor
@Inject constructor(private val loginRepository: LoginRepository) : UseCase<Boolean, SignupInteractor.Params>(){

    override suspend fun run(params: Params) = loginRepository.signup(params.signupModelPost)

    data class Params(val signupModelPost: SignupModelPost?)
}