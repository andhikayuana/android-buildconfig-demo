package id.yuana.buildconfig.demo.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.yuana.buildconfig.demo.App
import id.yuana.buildconfig.demo.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    private val repository by lazy {
        (application as App).repository
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnProfile.setOnClickListener {
            ProfileActivity.createIntent(this)
                .also { startActivity(it) }
        }
        binding.btnLogout.setOnClickListener {
            repository.logout()
            LoginActivity.createIntent(this)
                .also { startActivity(it) }
            finish()
        }
    }

    companion object {
        fun createIntent(context: Context) = Intent(context, HomeActivity::class.java)
    }
}