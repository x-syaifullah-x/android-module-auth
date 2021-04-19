package id.xxx.auth.data.email.repository.sign.verify

import id.xxx.auth.data.email.source.local.LocalDataSource
import id.xxx.auth.data.email.source.remote.RemoteDataSource
import id.xxx.auth.data.helper.get
import id.xxx.auth.domain.sign.verify.repository.IRepository
import id.xxx.base.domain.mediator.flow.NetworkBoundResourceImpl
import id.xxx.base.domain.model.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class RepositoryImpl(
    private val remote: RemoteDataSource,
    private val local: LocalDataSource
) : IRepository {

    override fun sendVerification(): Flow<Resource<String>> {
        val message = StringBuilder()
        return NetworkBoundResourceImpl(
            blockRequest = { remote.sendVerification() },
            blockOnRequest = { apiResponse, _ ->
                apiResponse.get(
                    blockSuccess = { message.replace(0, message.length, it) }
                )
            },
            blockResult = { flowOf(message.toString()) }
        ).asFlow()
    }

    override fun isVerify() = NetworkBoundResourceImpl(
        blockRequest = { remote.isVerify() },
        blockOnRequest = { apiResponse, _ ->
            apiResponse.get(
                blockSuccess = { local.setVerify(it) },
                blockEmpty = { local.setVerify(false) }
            )
        },
        blockResult = { local.currentUser().map { it?.isEmailVerify } }
    ).asFlow()
}