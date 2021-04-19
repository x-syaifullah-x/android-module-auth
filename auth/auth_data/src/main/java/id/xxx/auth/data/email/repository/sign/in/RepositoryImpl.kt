package id.xxx.auth.data.email.repository.sign.`in`

import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import id.xxx.auth.data.email.repository.sign.`in`.worker.Worker
import id.xxx.auth.data.email.source.local.LocalDataSource
import id.xxx.auth.data.email.source.mapper.toUserEntity
import id.xxx.auth.data.email.source.remote.RemoteDataSource
import id.xxx.auth.data.helper.Network
import id.xxx.auth.domain.sign.`in`.model.SignInModel
import id.xxx.auth.domain.sign.`in`.repository.IRepository
import id.xxx.base.domain.mediator.flow.NetworkBoundResourceImpl
import id.xxx.base.domain.model.ApiResponse
import kotlinx.coroutines.flow.map

class RepositoryImpl(
    private val remote: RemoteDataSource,
    private val local: LocalDataSource,
    private val onConnected: Network
) : IRepository {

    override fun signIn(email: String, password: String) = NetworkBoundResourceImpl(
        loadType = NetworkBoundResourceImpl.LoadMode.NETWORK_FIRST,
        blockRequest = { remote.signIn(email, password) },
        blockOnRequest = { apiResponse, model ->
            when (apiResponse) {
                is ApiResponse.Success -> local.signIn(apiResponse.data.toUserEntity(true))
                is ApiResponse.Empty -> model?.apply { local.remove(email) }
                is ApiResponse.Error -> {
                    if (apiResponse.error is FirebaseAuthInvalidUserException) local.remove(email)
                    if (apiResponse.error is FirebaseNetworkException) {
                        val userEntity = local.getUser(email).copy(isActive = true)
                        local.signIn(userEntity)
                        onConnected.onConnected(Worker::class.java, Worker.putData(email, password))
                    }
                }
            }
        },
        blockResult = {
            local.signIn(email).map { it?.run { SignInModel(id, email, isEmailVerify) } }
        }
    ).asFlow()
}