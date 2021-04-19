package id.xxx.auth.domain.sign.`in`.usecase

import id.xxx.auth.domain.sign.`in`.model.SignInModel
import id.xxx.base.domain.model.Resource
import kotlinx.coroutines.flow.Flow

interface IInteractor {

    fun signIn(email: String, password: String): Flow<Resource<SignInModel>>

}