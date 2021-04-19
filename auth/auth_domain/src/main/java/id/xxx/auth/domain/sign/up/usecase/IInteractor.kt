package id.xxx.auth.domain.sign.up.usecase

import id.xxx.auth.domain.sign.up.model.SignUpModel
import id.xxx.base.domain.model.Resource
import kotlinx.coroutines.flow.Flow

interface IInteractor {
    fun signUp(email: String, password: String): Flow<Resource<SignUpModel>>
}