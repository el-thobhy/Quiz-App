package com.elthobhy.quizkuy.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.elthobhy.quizkuy.R
import com.elthobhy.quizkuy.databinding.ActivityMainBinding
import com.elthobhy.quizkuy.ui.prepare.PrepareActivity

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.btPlay?.setOnClickListener {
            startActivity(Intent(this, PrepareActivity::class.java))
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}