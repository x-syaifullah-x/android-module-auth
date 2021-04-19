package id.xxx.auth.data.email.repository.sign.verify.di

import id.xxx.auth.data.email.repository.sign.verify.RepositoryImpl
import id.xxx.auth.domain.sign.verify.repository.IRepository
import org.koin.dsl.module

object AuthDataVerifyModule {
    val module = module {
        single<IRepository> { RepositoryImpl(get(), get()) }
    }
}