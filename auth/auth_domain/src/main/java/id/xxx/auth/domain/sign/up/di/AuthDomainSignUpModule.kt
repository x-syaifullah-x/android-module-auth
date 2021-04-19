package id.xxx.auth.domain.sign.up.di

import id.xxx.auth.domain.sign.up.usecase.IInteractor
import id.xxx.auth.domain.sign.up.usecase.InteractorImpl
import org.koin.dsl.module

object AuthDomainSignUpModule {
    val module = module {
        single<IInteractor> { InteractorImpl(get()) }
    }
}