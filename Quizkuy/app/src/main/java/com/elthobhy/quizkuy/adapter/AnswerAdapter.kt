package com.elthobhy.quizkuy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elthobhy.quizkuy.R
import com.elthobhy.quizkuy.databinding.ItemOptionBinding
import com.elthobhy.quizkuy.model.Answers

class AnswerAdapter:RecyclerView.Adapter<AnswerAdapter.ViewHolder>() {
    inner class ViewHolder(private val itemOptionBinding: ItemOptionBinding)
        :RecyclerView.ViewHolder(itemOptionBinding.root) {
        fun bind(answers: Answers) {
            itemOptionBinding.tvPilihan.text = answers.option
            itemOptionBinding.jawaban.text = answers.answer

            if (answers.isClick!!){
                inActiveCheck()
            }else{
                activeCheck()
            }

            itemView.setOnClickListener {
                for (i in 0 until answer.size){
                    answer[i].isClick = i == adapterPosition
                }
                notifyDataSetChanged()
            }
        }

        private fun activeCheck() {
            itemOptionBinding.ivCheck.setImageResource(R.drawable.ic_baseline_panorama_fish_eye_24)
        }

        private fun inActiveCheck() {
            itemOptionBinding.ivCheck.setImageResource(R.drawable.ic_baseline_check_circle_24)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemOptionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    private var answer= mutableListOf<Answers>()
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(answer[position])
    }

    override fun getItemCount(): Int = answer.size

    fun setData(answer: MutableList<Answers>){
        this.answer = answer
        notifyDataSetChanged()
    }
}