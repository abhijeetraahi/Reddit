package com.raahi.reddit.ui.comment

import com.raahi.reddit.data.network.model.CommentData
import com.raahi.reddit.data.network.model.response.CommentResponse
import com.raahi.reddit.ui.base.BaseContract

interface CommentContract {

    interface View: BaseContract.View{

        fun initToolBar()

        fun initView()

        fun setUpRecyclerView()

        fun hideProgressBar()

        fun showProgressBar()

    }

    interface Presenter<V: View>: BaseContract.Presenter<V>{

        fun setCommentData(commentData: CommentData)

        fun getCommentData(): CommentData

        fun getCommentList(): ArrayList<CommentResponse.Child>

        fun getAllComments()

        fun postComment(comment: String)
    }
}