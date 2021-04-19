package id.xxx.auth.di

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.core.module.Module

@ExperimentalCoroutinesApi
object AppModule {

    val modules = mutableListOf<Module>().apply {
        addAll(AuthComponent.getComponent())
    }.toList()

}