package id.xxx.auth

import androidx.room.Database
import androidx.room.RoomDatabase
import id.xxx.auth.data.email.source.local.dao.IAuthDao
import id.xxx.auth.data.email.source.local.entity.UserEntity

@Database(
    entities = [
        UserEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AuthDatabase : RoomDatabase(), IAuthDao