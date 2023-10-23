package id.yuana.buildconfig.demo.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import id.yuana.buildconfig.demo.App
import id.yuana.buildconfig.demo.BuildConfig
import id.yuana.buildconfig.demo.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    private val repository by lazy {
        (application as App).repository
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        lifecycleScope.launch {
            delay(500)
            if (BuildConfig.ENTRY_POINT_ENABLED) {
                needByPassAndDetermineNextScreen()
            } else {
                determineNextScreen()
            }
        }
    }

    private fun needByPassAndDetermineNextScreen() {
        val entryPointConfig = BuildConfig.ENTRY_POINT_CONFIG
        if (repository.alreadyLogin()) {
            startActivity(Intent(this, entryPointConfig.activityClass))
            finish()
        } else {
            val success = repository.login(
                email = entryPointConfig.email,
                password = entryPointConfig.password
            )
            if (success) {
                startActivity(Intent(this, entryPointConfig.activityClass))
                finish()
            } else {
                Toast.makeText(this, getString(R.string.msg_login_failed), Toast.LENGTH_LONG).show()
                determineNextScreen()
            }
        }
    }

    private fun determineNextScreen() {
        val nextIntent = if (repository.alreadyLogin()) {
            HomeActivity.createIntent(this)
        } else {
            LoginActivity.createIntent(this)
        }

        startActivity(nextIntent)
        finish()
    }
}