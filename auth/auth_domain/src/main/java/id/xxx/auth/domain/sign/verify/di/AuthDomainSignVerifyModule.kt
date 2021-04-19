package id.xxx.auth.domain.sign.verify.di

import id.xxx.auth.domain.sign.verify.usecase.IInteractor
import id.xxx.auth.domain.sign.verify.usecase.InteractorImpl
import org.koin.dsl.module

object AuthDomainSignVerifyModule {
    val module = module {
        single<IInteractor> { InteractorImpl(get()) }
    }
}