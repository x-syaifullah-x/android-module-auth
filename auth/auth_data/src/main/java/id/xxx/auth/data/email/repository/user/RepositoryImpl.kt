package id.xxx.auth.data.email.repository.user

import id.xxx.auth.data.email.source.local.LocalDataSource
import id.xxx.auth.data.email.source.mapper.toUserModel
import id.xxx.auth.data.email.source.remote.RemoteDataSource
import id.xxx.auth.data.helper.get
import id.xxx.auth.domain.user.repository.IRepository
import id.xxx.base.domain.mediator.flow.NetworkBoundResourceImpl
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class RepositoryImpl(
    private val remote: RemoteDataSource,
    private val local: LocalDataSource
) : IRepository {

    override fun currentUser() = NetworkBoundResourceImpl(
        loadType = NetworkBoundResourceImpl.LoadMode.NETWORK_FIRST,
        blockRequest = { remote.currentUser() },
        blockResult = { local.currentUser().map { it?.run { it.toUserModel() } } }
    ).asFlow()

    override fun signOut() = NetworkBoundResourceImpl(
        loadType = NetworkBoundResourceImpl.LoadMode.NETWORK_FIRST,
        blockRequest = { remote.signOut() },
        blockOnRequest = { apiResponse, _ ->
            apiResponse.get(
                blockSuccess = { local.signOut() }
            )
        },
        blockResult = { flow { emit(local.onSignOut()) } },
    ).asFlow()
}