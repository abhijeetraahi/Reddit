package com.raahi.reddit.ui.subreddit

import com.raahi.reddit.data.network.model.CommentData
import com.raahi.reddit.data.network.model.response.SubRedditResponse
import com.raahi.reddit.ui.base.BaseContract

interface SubRedditContract {

    interface View: BaseContract.View{

        fun initToolBar()

        fun setUpRecyclerView()

        fun getFeed(view: android.view.View)

        fun hideProgressBar()

        fun showProgressBar()

        fun launchCommentsActivity(commentData: CommentData)

    }

    interface Presenter<V: View>: BaseContract.Presenter<V>{

        fun getSubRedditData(subReddit: String)

        fun getSubRedditResponse(): SubRedditResponse

        fun onCommentClicked(position: Int)

    }
}