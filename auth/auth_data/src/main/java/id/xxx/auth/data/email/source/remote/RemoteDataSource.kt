package id.xxx.auth.data.email.source.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessaging
import id.xxx.auth.data.email.source.mapper.toUserResponse
import id.xxx.base.domain.model.getApiResponseAsFlow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withTimeout

class RemoteDataSource(private val auth: FirebaseAuth, private val messaging: FirebaseMessaging) {

    fun signUp(email: String, password: String) = getApiResponseAsFlow(
        blockFetch = {
            auth.createUserWithEmailAndPassword(email, password).await()
                ?.user?.toUserResponse()
        }
    )

    fun sendVerification() = getApiResponseAsFlow(
        blockFetch = {
            if (auth.currentUser?.isEmailVerified == true) return@getApiResponseAsFlow "is email verified"
            auth.currentUser?.sendEmailVerification()?.await()
            "Please Check Your Mail And Open Link To Verify"
        }
    )

    fun isVerify() = getApiResponseAsFlow(
        blockFetch = {
            auth.currentUser?.reload()?.await()
            auth.currentUser?.isEmailVerified
        }
    )

    fun signIn(email: String, password: String) = getApiResponseAsFlow(
        blockFetch = {
            auth.signInWithEmailAndPassword(email, password).await().user?.run {
                messaging.subscribeToTopic(uid).await()
                return@run toUserResponse()
            }
        }
    )

    fun signOut() = getApiResponseAsFlow(
        blockFetch = {
            withTimeout(3000){
                auth.currentUser?.run {
                    auth.signOut()
                    messaging.unsubscribeFromTopic(uid).await()
                    auth.currentUser == null
                } ?: true
            }
        }
    )

    fun currentUser() = getApiResponseAsFlow(
        blockFetch = {
            withTimeout(3000) {
                auth.currentUser?.run {
                    messaging.subscribeToTopic(uid).await()
                    return@run toUserResponse()
                }
            }
        }
    )
}