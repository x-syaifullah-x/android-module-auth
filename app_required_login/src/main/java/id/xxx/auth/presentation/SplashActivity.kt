package id.xxx.auth.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import id.xxx.auth.domain.user.usecase.IInteractor
import id.xxx.auth.presentation.ui.AuthActivity
import id.xxx.auth.presentation.ui.AuthActivity.Companion.putAuthDestination
import id.xxx.base.domain.model.Resource
import id.xxx.base.presentation.extension.hideSystemUI
import id.xxx.base.presentation.extension.openActivityAndFinish
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import org.koin.android.ext.android.inject

class SplashActivity : AppCompatActivity() {

    private val interactor by inject<IInteractor>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launchWhenCreated {
            val result = interactor.currentUser().drop(1).first()
            if (result is Resource.Success) {
                if (result.data.isVerify) {
                    openActivityAndFinish<MainActivity>()
                } else {
                    openActivityAndFinish<AuthActivity> { putAuthDestination(MainActivity::class) }
                }
            } else {
                openActivityAndFinish<AuthActivity> { putAuthDestination(MainActivity::class) }
            }
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        if (hasFocus) hideSystemUI()
    }
}