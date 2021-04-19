package id.xxx.auth.data.service

import android.content.Intent
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import id.xxx.auth.domain.user.usecase.IInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MessagingService : FirebaseMessagingService() {

    private val iInteractor by inject<IInteractor>()

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("TAG", "Refreshed token: $token")
    }

    override fun handleIntent(p0: Intent?) {
        super.handleIntent(p0)

        GlobalScope.launch(Dispatchers.Default) {
            iInteractor.signOut().drop(1).firstOrNull()
        }
    }
}