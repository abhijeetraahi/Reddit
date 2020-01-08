package com.raahi.reddit.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.raahi.reddit.R
import com.raahi.reddit.databinding.HomeItemBinding
import com.raahi.reddit.ui.home.HomeContract
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.home_item.view.*
import kotlinx.android.synthetic.main.sub_reddit_item.view.tvComment
import javax.inject.Inject

class HomeAdapter @Inject
constructor(private val presenter: HomeContract.Presenter<HomeContract.View>) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.home_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return presenter.getHomeResponse().data?.children?.size!!
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.model = presenter.getHomeResponse().data?.children?.get(position)?.data

        Picasso.get()
            .load(presenter.getHomeResponse().data?.children?.get(position)?.data?.thumbnail)
            .into(holder.binding.imageView)

        Picasso.get()
            .load(presenter.getHomeResponse().data?.children?.get(position)?.data?.thumbnail)
            .into(holder.binding.ivThumbnail)
    }

    inner class ViewHolder(val binding: HomeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.tvComment.setOnClickListener {
                presenter.onCommentClicked(adapterPosition)
            }

            binding.root.upVote.setOnClickListener{
                presenter.submiteVote(1, adapterPosition)
            }

            binding.root.downVote.setOnClickListener{
                presenter.submiteVote(-1, adapterPosition)
            }
        }
    }
}