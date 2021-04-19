package id.xxx.auth.domain.sign.verify.repository

import id.xxx.base.domain.model.Resource
import kotlinx.coroutines.flow.Flow

interface IRepository {

    fun sendVerification(): Flow<Resource<String>>

    fun isVerify(): Flow<Resource<Boolean>>

}