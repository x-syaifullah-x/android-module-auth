package id.xxx.auth.di

import id.xxx.auth.data.di.AutDataModule
import id.xxx.auth.domain.di.AuthDomainModule
import id.xxx.auth.presentation.di.AuthPresentationModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.core.module.Module

@ExperimentalCoroutinesApi
object AuthComponent {

    private val component = mutableListOf<Module>().apply {
        addAll(AutDataModule.modules)
        addAll(AuthDomainModule.modules)
        addAll(AuthPresentationModule.modules)
    }

    fun getComponent(includeDatabase: Boolean = true): List<Module> {
        val component =
            if (includeDatabase) {
                component.apply { add(AuthDatabaseModule.appDatabase) }
            } else {
                component
            }
        return component.toList()
    }
}