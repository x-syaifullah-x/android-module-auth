package id.xxx.auth.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import id.xxx.auth.R
import id.xxx.auth.presentation.ui.AuthActivity
import id.xxx.base.domain.model.get
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar))

        val btnSignOut = findViewById<View>(R.id.btn_sign_out)
        val btnSignIn = findViewById<View>(R.id.btn_sign_in)

        viewModel.currentUser.observe(this) {
            it.get(
                blockSuccess = {
                    btnSignIn.isVisible = false
                    btnSignOut.isVisible = true
                },
                blockEmpty = {
                    btnSignIn.isVisible = true
                    btnSignOut.isVisible = false
                },
                blockError = { user, _ ->
                    Toast.makeText(this, "viewModel.currentUser() error", Toast.LENGTH_SHORT).show()
                    btnSignIn.isVisible = user == null
                    btnSignOut.isVisible = user != null
                }
            )
        }

        btnSignIn.setOnClickListener {
            startActivityForResult(Intent(this, AuthActivity::class.java), 132)
        }

        btnSignOut.setOnClickListener { view ->
            viewModel.signOut().observe(this) {
                it.get(
                    blockLoading = { view.isEnabled = false },
                    blockError = { value, _ ->
                        Toast.makeText(this, "sign out error", Toast.LENGTH_SHORT).show()
                        view.isEnabled = value ?: false
                    },
                    blockSuccess = { value -> view.isEnabled = !value }
                )
            }
        }
    }
}