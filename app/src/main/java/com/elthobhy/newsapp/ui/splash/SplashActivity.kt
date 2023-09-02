package com.elthobhy.newsapp.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.elthobhy.newsapp.databinding.ActivitySplashBinding
import com.elthobhy.newsapp.ui.MainActivity
import com.elthobhy.newsapp.ui.auth.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        goToMain()
    }

    private fun goToMain() {
        Handler(Looper.getMainLooper())
            .postDelayed({
                checkAuth()
            }, DELAY)
    }

    private fun checkAuth() {
        if (FirebaseAuth.getInstance().currentUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finishAffinity()
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
            finishAffinity()
        }
    }

    companion object {
        const val DELAY: Long = 2000
    }
}