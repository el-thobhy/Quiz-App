package com.elthobhy.quizkuy.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.elthobhy.quizkuy.databinding.ItemContentBinding
import com.elthobhy.quizkuy.model.Answers
import com.elthobhy.quizkuy.model.Content
import com.elthobhy.quizkuy.model.Contents

class ContentAdapter: RecyclerView.Adapter<ContentAdapter.ViewHolder>() {
    class ViewHolder(private val itemContentBinding: ItemContentBinding)
        : RecyclerView.ViewHolder(itemContentBinding.root) {
        fun bind(content: Contents) {
            val adapterAnswer = AnswerAdapter()
            itemContentBinding.tvBody.text = content.body
            if(content.image?.isNotEmpty() == true){
                itemContentBinding.ivQuestion.visibility = View.VISIBLE
                Glide.with(itemView)
                    .load(content.image)
                    .placeholder(android.R.color.darker_gray)
                    .into(itemContentBinding.ivQuestion)
            }else{
                itemContentBinding.ivQuestion.visibility = View.GONE
            }

            if (content.answers != null){
                adapterAnswer.setData(content.answers as MutableList<Answers>)
                itemContentBinding.rvAnswer.adapter = adapterAnswer
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    private var content= mutableListOf<Contents>()
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(content[position])
    }

    override fun getItemCount(): Int = content.size

    fun setData(content: MutableList<Contents>){
        this.content=content
        notifyDataSetChanged()
    }

    fun getResult(): MutableList<Contents>{
        return content
    }
}