package id.xxx.auth.data.email.repository.user.di

import id.xxx.auth.data.email.repository.user.RepositoryImpl
import id.xxx.auth.domain.user.repository.IRepository
import org.koin.dsl.module

object AuthDataUserModule {
    val module = module {
        single<IRepository> { RepositoryImpl(get(), get()) }
    }
}