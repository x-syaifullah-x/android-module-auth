package id.xxx.auth.domain.sign.`in`.repository

import id.xxx.auth.domain.sign.`in`.model.SignInModel
import id.xxx.base.domain.model.Resource
import kotlinx.coroutines.flow.Flow

interface IRepository {

    fun signIn(email: String, password: String): Flow<Resource<SignInModel>>

}