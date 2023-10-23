package id.yuana.buildconfig.demo.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.yuana.buildconfig.demo.App
import id.yuana.buildconfig.demo.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    private val repository by lazy {
        (application as App).repository
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.etEmail.text = repository.loginStatus.keys.first()
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    companion object {
        fun createIntent(context: Context) = Intent(context, ProfileActivity::class.java)
    }
}