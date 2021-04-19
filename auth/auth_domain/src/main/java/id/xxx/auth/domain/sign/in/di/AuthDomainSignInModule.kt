package id.xxx.auth.domain.sign.`in`.di

import id.xxx.auth.domain.sign.`in`.usecase.IInteractor
import id.xxx.auth.domain.sign.`in`.usecase.InteractorImpl
import org.koin.dsl.module

object AuthDomainSignInModule {
    val module = module {
        single<IInteractor> { InteractorImpl(get()) }
    }
}