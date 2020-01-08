package com.raahi.reddit.ui.home

import com.raahi.reddit.data.network.model.CommentData
import com.raahi.reddit.data.network.model.response.HomeResponse
import com.raahi.reddit.ui.base.BaseContract

interface HomeContract {

    interface View: BaseContract.View{

        fun initToolBar()

        fun setUpRecyclerView()

        fun launchSubRedditActivity(view: android.view.View)

        fun hideProgressBar()

        fun showProgressBar()

        fun showBottomSheet(view: android.view.View)

        fun launchCommentsActivity(commentData: CommentData)

    }

    interface Presenter<V: View>: BaseContract.Presenter<V>{

        fun getHomeData(filterType: String)

        fun getHomeResponse(): HomeResponse

        fun onCommentClicked(position: Int)

        fun submiteVote(dir: Int, position: Int)
    }
}