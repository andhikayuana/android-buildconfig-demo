package id.yuana.buildconfig.demo.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import id.yuana.buildconfig.demo.App
import id.yuana.buildconfig.demo.R
import id.yuana.buildconfig.demo.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val repository by lazy {
        (application as App).repository
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnLogin.setOnClickListener {
            val success = repository.login(
                binding.etEmail.text.toString().trim(),
                binding.etPassword.text.toString().trim()
            )

            if (success) {
                HomeActivity.createIntent(this)
                    .also { startActivity(it) }
                finish()
            } else {
                Toast.makeText(this, getString(R.string.msg_login_failed), Toast.LENGTH_LONG).show()
            }
        }
    }

    companion object {
        fun createIntent(context: Context) = Intent(context, LoginActivity::class.java)
    }
}