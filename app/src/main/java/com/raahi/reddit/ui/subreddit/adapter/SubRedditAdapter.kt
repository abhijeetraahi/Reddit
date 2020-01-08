package com.raahi.reddit.ui.subreddit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.raahi.reddit.R
import com.raahi.reddit.databinding.SubRedditItemBinding
import com.raahi.reddit.ui.subreddit.SubRedditContract
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.sub_reddit_item.view.*
import javax.inject.Inject

class SubRedditAdapter @Inject constructor(private val presenter: SubRedditContract.Presenter<SubRedditContract.View>) :
    RecyclerView.Adapter<SubRedditAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.sub_reddit_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return presenter.getSubRedditResponse().data?.children?.size!!
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.model = presenter.getSubRedditResponse().data?.children?.get(position)?.data

        Picasso.get()
            .load(presenter.getSubRedditResponse().data?.children?.get(position)?.data?.thumbnail)
            .into(holder.binding.imageView)

        Picasso.get()
            .load(presenter.getSubRedditResponse().data?.children?.get(position)?.data?.thumbnail)
            .into(holder.binding.ivThumbnail)
    }

    inner class ViewHolder(val binding: SubRedditItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.tvComment.setOnClickListener{
                presenter.onCommentClicked(adapterPosition)
            }
        }
    }
}