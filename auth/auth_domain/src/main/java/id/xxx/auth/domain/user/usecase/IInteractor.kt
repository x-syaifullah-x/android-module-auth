package id.xxx.auth.domain.user.usecase

import id.xxx.auth.domain.user.model.UserModel
import id.xxx.base.domain.model.Resource
import kotlinx.coroutines.flow.Flow

interface IInteractor {

    fun currentUser(): Flow<Resource<UserModel>>

    fun signOut(): Flow<Resource<Boolean>>

}