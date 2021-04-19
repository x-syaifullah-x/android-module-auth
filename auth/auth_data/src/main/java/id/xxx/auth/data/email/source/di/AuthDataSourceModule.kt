package id.xxx.auth.data.email.source.di

import id.xxx.auth.data.email.source.local.di.LocalModule
import id.xxx.auth.data.email.source.remote.di.RemoteModule
import org.koin.core.module.Module

object AuthDataSourceModule {

    val modules = mutableListOf<Module>().apply {
        addAll(LocalModule.modules)
        addAll(RemoteModule.modules)
    }.toList()
}