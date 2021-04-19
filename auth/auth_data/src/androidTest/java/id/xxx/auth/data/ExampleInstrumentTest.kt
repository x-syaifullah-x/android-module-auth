package id.xxx.auth.data

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessaging
import id.xxx.auth.data.email.source.local.database.AppDatabase
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ExampleInstrumentTest {
//    private val database = Room.databaseBuilder(
//        ApplicationProvider.getApplicationContext(),
//        AppDatabase::class.java,
//        "database"
//    ).build()

//    private val databaseInMemory = Room.inMemoryDatabaseBuilder(
//        ApplicationProvider.getApplicationContext(), AppDatabase::class.java,
//    ).build()

    private val firebaseAuth = FirebaseAuth.getInstance()

    private val firebaseMessaging = FirebaseMessaging.getInstance()

    @Test
    fun test() = runBlocking {

    }
}