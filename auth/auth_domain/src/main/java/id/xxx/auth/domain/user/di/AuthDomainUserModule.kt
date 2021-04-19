package id.xxx.auth.domain.user.di

import id.xxx.auth.domain.user.usecase.IInteractor
import id.xxx.auth.domain.user.usecase.InteractorImpl
import org.koin.dsl.module

object AuthDomainUserModule {
    val module = module {
        single<IInteractor> { InteractorImpl(get()) }
    }
}