package id.xxx.auth.data.email.repository.sign.up.di

import id.xxx.auth.data.email.repository.sign.up.RepositoryImpl
import id.xxx.auth.domain.sign.up.repository.IRepository
import org.koin.dsl.module

object AuthDataSignUpModule {
    val module = module {
        single<IRepository> { RepositoryImpl(get(), get()) }
    }
}