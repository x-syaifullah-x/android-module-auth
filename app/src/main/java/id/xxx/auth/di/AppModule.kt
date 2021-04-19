package id.xxx.auth.di

import id.xxx.auth.presentation.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

@ExperimentalCoroutinesApi
object AppModule {

    private val viewModelModule = module {
        viewModel { MainViewModel(get()) }
    }

    val modules = mutableListOf<Module>(
        viewModelModule
    ).apply {
        addAll(AuthComponent.getComponent())
    }.toList()

}