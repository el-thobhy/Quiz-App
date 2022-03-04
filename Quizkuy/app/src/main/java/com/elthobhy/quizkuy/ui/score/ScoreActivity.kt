package com.elthobhy.quizkuy.ui.score

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.elthobhy.quizkuy.R
import com.elthobhy.quizkuy.databinding.ActivityScoreBinding
import com.elthobhy.quizkuy.ui.main.MainActivity

class ScoreActivity : AppCompatActivity() {

    private var _binding: ActivityScoreBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityScoreBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val nickname = intent.getStringExtra(EXTRA_NICKNAME)
        val score = intent.getIntExtra(EXTRA_SCORE,0)

        binding?.tvName?.text = nickname
        binding?.score?.text = score.toString()
        binding?.tvDone?.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
        finishAffinity()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object{
        const val EXTRA_SCORE = "extra_score"
        const val EXTRA_NICKNAME = "extra_nickname"
    }
}