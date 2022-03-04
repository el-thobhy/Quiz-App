package com.elthobhy.quizkuy.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.core.os.HandlerCompat.postDelayed
import com.elthobhy.quizkuy.R
import com.elthobhy.quizkuy.databinding.ActivitySplashBinding
import com.elthobhy.quizkuy.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {

    private var _binding: ActivitySplashBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finishAffinity()
        },1200)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}