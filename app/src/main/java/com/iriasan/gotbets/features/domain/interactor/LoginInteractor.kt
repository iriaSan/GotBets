package com.iriasan.gotbets.features.domain.interactor


import com.iriasan.gotbets.features.domain.models.LoginModelPost
import com.iriasan.gotbets.features.domain.models.UserModel
import com.iriasan.gotbets.core.interactor.UseCase
import com.iriasan.gotbets.features.data.repository.LoginRepository
import javax.inject.Inject

class LoginInteractor
@Inject constructor(private val loginRepository: LoginRepository) : UseCase<Boolean, LoginInteractor.Params>() {

    override suspend fun run(params: Params) = loginRepository.login(params.loginModelPost)

    data class Params(val loginModelPost: LoginModelPost?)
}
