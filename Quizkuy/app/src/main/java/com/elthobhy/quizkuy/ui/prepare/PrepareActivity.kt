package com.elthobhy.quizkuy.ui.prepare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.elthobhy.quizkuy.R
import com.elthobhy.quizkuy.databinding.ActivityPrepareBinding
import com.elthobhy.quizkuy.ui.content.ContentActivity

class PrepareActivity : AppCompatActivity() {

    private var _binding: ActivityPrepareBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityPrepareBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        onClick()
    }

    private fun onClick() {
        binding?.btStart?.setOnClickListener {
            val nickname = binding?.etNickname?.text.toString()
            if (checkValidation(nickname)){
                val intent = Intent(this, ContentActivity::class.java)
                intent.putExtra(ContentActivity.EXTRA_NICKNAME,nickname)
                startActivity(Intent(intent))
                Log.d("nickname", "onClick: $nickname")
            }
        }
    }

    private fun checkValidation(nickname: String): Boolean {
        return if(nickname.isEmpty()){
            binding?.etNickname?.error = getString(R.string.field_your_nickname)
            false
        }else{
            true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}