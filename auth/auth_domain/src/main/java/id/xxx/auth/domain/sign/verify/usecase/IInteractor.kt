package id.xxx.auth.domain.sign.verify.usecase

import id.xxx.base.domain.model.Resource
import kotlinx.coroutines.flow.Flow

interface IInteractor {
    fun sendVerification(): Flow<Resource<String>>

    fun isVerify(): Flow<Resource<Boolean>>
}