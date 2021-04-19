package id.xxx.auth.di

import androidx.room.Room
import id.xxx.auth.AuthDatabase
import org.koin.dsl.module

object AuthDatabaseModule {
    val appDatabase = module {
        single {
            Room.databaseBuilder(get(), AuthDatabase::class.java, "id.xxx.auth").build()
        }
        single { get<AuthDatabase>().userDao() }
    }
}