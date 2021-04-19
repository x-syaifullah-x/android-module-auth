package id.xxx.auth.data.email.repository.sign.up

import id.xxx.auth.data.email.source.local.LocalDataSource
import id.xxx.auth.data.email.source.mapper.toSignUpModel
import id.xxx.auth.data.email.source.mapper.toUserEntity
import id.xxx.auth.data.email.source.remote.RemoteDataSource
import id.xxx.auth.data.helper.get
import id.xxx.auth.domain.sign.up.repository.IRepository
import id.xxx.base.domain.mediator.flow.NetworkBoundResourceImpl
import kotlinx.coroutines.flow.map

class RepositoryImpl(
    private val remote: RemoteDataSource,
    private val local: LocalDataSource
) : IRepository {

    override fun signUp(email: String, password: String) = NetworkBoundResourceImpl(
        blockRequest = { remote.signUp(email, password) },
        blockOnRequest = { apiResponse, _ ->
            apiResponse.get(
                blockSuccess = { local.signUp(it.toUserEntity(true)) }
            )
        },
        blockResult = { local.currentUser().map { it?.toSignUpModel() } }
    ).asFlow()
}