package id.xxx.auth.data.email.repository.sign.`in`.di

import id.xxx.auth.data.helper.Network
import id.xxx.auth.data.email.repository.sign.`in`.RepositoryImpl
import id.xxx.auth.domain.sign.`in`.repository.IRepository
import org.koin.dsl.module

object AuthDataSignModule {
    val module = module {
        single<IRepository> { RepositoryImpl(get(), get(), Network(get())) }
    }
}