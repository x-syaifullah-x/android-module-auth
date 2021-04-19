package id.xxx.auth.domain.di

import id.xxx.auth.domain.sign.`in`.di.AuthDomainSignInModule
import id.xxx.auth.domain.sign.up.di.AuthDomainSignUpModule
import id.xxx.auth.domain.sign.verify.di.AuthDomainSignVerifyModule
import id.xxx.auth.domain.user.di.AuthDomainUserModule

object AuthDomainModule {
    val modules = mutableListOf(
        AuthDomainSignUpModule.module,
        AuthDomainSignInModule.module,
        AuthDomainSignVerifyModule.module,
        AuthDomainUserModule.module,
    ).toList()
}