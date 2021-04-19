package id.xxx.auth.data.email.repository.di

import id.xxx.auth.data.email.repository.sign.`in`.di.AuthDataSignModule
import id.xxx.auth.data.email.repository.sign.up.di.AuthDataSignUpModule
import id.xxx.auth.data.email.repository.sign.verify.di.AuthDataVerifyModule
import id.xxx.auth.data.email.repository.user.di.AuthDataUserModule

object AuthDataRepositoryModule {

    val modules = listOf(
        AuthDataSignUpModule.module,
        AuthDataVerifyModule.module,
        AuthDataUserModule.module,
        AuthDataSignModule.module
    )
}