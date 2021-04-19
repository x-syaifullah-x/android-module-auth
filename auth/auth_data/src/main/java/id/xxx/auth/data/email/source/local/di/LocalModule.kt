package id.xxx.auth.data.email.source.local.di

import id.xxx.auth.data.email.source.local.LocalDataSource
import org.koin.dsl.module

object LocalModule {

    private val localDataSource = module {
        single { LocalDataSource(get()) }
    }

    val modules = listOf(
        localDataSource,
    )
}