package com.elthobhy.quizkuy.ui.content

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.elthobhy.quizkuy.R
import com.elthobhy.quizkuy.adapter.ContentAdapter
import com.elthobhy.quizkuy.databinding.ActivityContentBinding
import com.elthobhy.quizkuy.model.Contents
import com.elthobhy.quizkuy.repository.Repository
import com.elthobhy.quizkuy.ui.content.ContentActivity.Companion.EXTRA_NICKNAME
import com.elthobhy.quizkuy.ui.main.MainActivity
import com.elthobhy.quizkuy.ui.prepare.PrepareActivity
import com.elthobhy.quizkuy.ui.score.ScoreActivity

class ContentActivity : AppCompatActivity() {

    private var _binding: ActivityContentBinding? = null
    private val binding get() = _binding
    private lateinit var contentAdapter: ContentAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private var dataSize = 0
    private var currentPosition = 0
    private var nickname: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityContentBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        //init
        contentAdapter = ContentAdapter()

        //getData
        if (intent!=null){
            nickname =   intent.getStringExtra(EXTRA_NICKNAME)
        }
        if (savedInstanceState!=null){
            nickname = savedInstanceState.getString(EXTRA_NICKNAME)
            val content = savedInstanceState.getParcelableArrayList<Contents>(EXTRA_CONTENT)
            showDataContent(content)
        }else{
            val contents = Repository.getData(this)
            showDataContent(contents)
        }

        onClick()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(EXTRA_NICKNAME,nickname)
        val content = contentAdapter.getResult()
        outState.putParcelableArrayList(EXTRA_CONTENT, content as ArrayList)
    }

    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setTitle("Are You Sure")
            .setMessage("Your Data Will Be Destroy")
            .setPositiveButton("yes"){dialog,_->
                dialog.dismiss()
                startActivity(Intent(this, MainActivity::class.java))
                finishAffinity()
                super.onBackPressed()
            }
            .setNegativeButton("No"){dialog,_->
                dialog.dismiss()
            }
            .show()

    }

    private fun onClick() {
        binding?.btNext?.setOnClickListener {
            if(currentPosition < dataSize -1){
                binding?.rvContent?.smoothScrollToPosition(currentPosition+1)
            }else{
                AlertDialog.Builder(this)
                    .setTitle("Are You Sure")
                    .setMessage("Jawaban Anda Akan disimpan")
                    .setPositiveButton("Yes"){dialog,_->
                        val contents = contentAdapter.getResult()
                        val totalQuiz = contents.size
                        var totalCorrect = 0

                        for(content in contents){
                            for(answer in content.answers!!){
                                if(answer?.isClick == true && answer.correctAnswer == true){
                                    totalCorrect += 1
                                }
                            }
                        }

                        val totalScore = totalCorrect.toDouble() / totalQuiz*100

                        val intent = Intent(this, ScoreActivity::class.java)
                        intent.putExtra(ScoreActivity.EXTRA_NICKNAME, nickname)
                        intent.putExtra(ScoreActivity.EXTRA_SCORE, totalScore.toInt())
                        startActivity(intent)
                        dialog.dismiss()
                        Log.d("total", "onClick: $totalScore, $nickname")
                    }
                    .setNegativeButton("No"){dialog,_->
                        dialog.dismiss()
                    }
                    .show()
            }
        }
        binding?.btPrev?.setOnClickListener {
            binding?.rvContent?.smoothScrollToPosition(currentPosition-1)
        }
    }

    private fun showDataContent(contents: List<Contents>?) {
        layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val snapHelper = PagerSnapHelper()

        if(contents!=null){
            contentAdapter.setData(contents as MutableList<Contents>)
        }
        snapHelper.attachToRecyclerView(binding?.rvContent)
        binding?.rvContent?.layoutManager = layoutManager
        binding?.rvContent?.adapter = contentAdapter

        dataSize = layoutManager.itemCount
        binding?.pbContent?.max = dataSize - 1

        var index = "${currentPosition + 1} / $dataSize"
        binding?.tvIndex?.text = index

        binding?.rvContent?.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                currentPosition = layoutManager.findFirstVisibleItemPosition()
                index = "${currentPosition + 1} / $dataSize"
                binding?.tvIndex?.text = index
                binding?.pbContent?.progress = currentPosition
            }
        })

    }

    companion object{
        const val EXTRA_NICKNAME = "extra_nickname"
        const val EXTRA_CONTENT = "extra_content"
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}