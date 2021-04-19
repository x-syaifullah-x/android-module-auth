package id.xxx.auth.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import id.xxx.auth.R
import id.xxx.auth.domain.user.usecase.IInteractor
import id.xxx.auth.presentation.ui.AuthActivity
import id.xxx.auth.presentation.ui.AuthActivity.Companion.putAuthDestination
import id.xxx.base.domain.model.Resource
import id.xxx.base.domain.model.get
import id.xxx.base.presentation.extension.openActivityAndFinish
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.firstOrNull
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val interactor by inject<IInteractor>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar))

        val btnSignOut = findViewById<View>(R.id.btn_sign_out)

        lifecycleScope.launchWhenCreated {
            btnSignOut.isEnabled =
                interactor.currentUser().drop(1).firstOrNull() is Resource.Success
        }

        btnSignOut.setOnClickListener { view ->
            interactor.signOut().asLiveData().observe(this) {
                it.get(
                    blockLoading = { view.isEnabled = false },
                    blockError = { _, _ -> view.isEnabled = true },
                    blockSuccess = {
                        openActivityAndFinish<AuthActivity> { putAuthDestination(MainActivity::class) }
                    }
                )
            }
        }
    }
}