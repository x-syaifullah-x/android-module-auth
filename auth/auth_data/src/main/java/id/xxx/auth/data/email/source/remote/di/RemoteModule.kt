package id.xxx.auth.data.email.source.remote.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessaging
import id.xxx.auth.data.email.source.remote.RemoteDataSource
import org.koin.dsl.module

object RemoteModule {
    private val firebaseAuth = module {
        single { FirebaseAuth.getInstance() }
    }

    private val firebaseMessaging = module {
        single { FirebaseMessaging.getInstance() }
    }


    private val remoteDataSource = module {
        single { RemoteDataSource(get(), get()) }
    }

    val modules = listOf(
        firebaseAuth,
        firebaseMessaging,
        remoteDataSource,
    )
}