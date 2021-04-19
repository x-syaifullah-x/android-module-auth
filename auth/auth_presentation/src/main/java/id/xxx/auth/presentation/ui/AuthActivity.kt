package id.xxx.auth.presentation.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import id.xxx.auth.presentation.R
import id.xxx.auth.presentation.databinding.ActivityAuthBinding
import id.xxx.base.presentation.binding.activity.BaseActivityWithNavigation
import id.xxx.base.presentation.binding.delegate.viewBinding
import org.koin.ext.getFullName
import kotlin.reflect.KClass

class AuthActivity : BaseActivityWithNavigation<ActivityAuthBinding>() {

    companion object {
        private const val DATA_DESTINATION_CLASS = "DATA_DESTINATION_CLASS"

        fun <T : Activity> Intent.putAuthDestination(activityDes: KClass<out T>) {
            putExtra(DATA_DESTINATION_CLASS, activityDes.getFullName())
        }

        fun Intent.getAuthDestination() = getStringExtra(DATA_DESTINATION_CLASS)
    }

    override val binding by viewBinding { ActivityAuthBinding.inflate(it) }

    override fun getIdNavHost() = R.id.nav_host_auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(navHostFragment.findNavController())
    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp() || navHostFragment.findNavController().navigateUp()
    }
}