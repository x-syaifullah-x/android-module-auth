package id.xxx.auth.data.di

import id.xxx.auth.data.email.repository.di.AuthDataRepositoryModule
import id.xxx.auth.data.email.source.di.AuthDataSourceModule
import org.koin.core.module.Module

object AutDataModule {

    val modules = mutableListOf<Module>().apply {
        addAll(AuthDataSourceModule.modules)
        addAll(AuthDataRepositoryModule.modules)
    }.toList()
}