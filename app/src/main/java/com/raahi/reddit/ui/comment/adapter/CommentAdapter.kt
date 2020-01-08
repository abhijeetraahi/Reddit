package com.raahi.reddit.ui.comment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.raahi.reddit.R
import com.raahi.reddit.databinding.CommentItemBinding
import com.raahi.reddit.ui.comment.CommentContract
import javax.inject.Inject

class CommentAdapter @Inject
constructor(private val presenter: CommentContract.Presenter<CommentContract.View>) :
    RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.comment_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return presenter.getCommentList().size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.model = presenter.getCommentList()[position]
    }

    inner class ViewHolder(val binding: CommentItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}