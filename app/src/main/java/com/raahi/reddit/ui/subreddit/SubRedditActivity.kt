package com.raahi.reddit.ui.subreddit

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.raahi.reddit.R
import com.raahi.reddit.data.network.model.CommentData
import com.raahi.reddit.databinding.SubRedditActivityBinding
import com.raahi.reddit.ui.base.BaseActivity
import com.raahi.reddit.ui.comment.CommentActivity
import com.raahi.reddit.ui.subreddit.adapter.SubRedditAdapter
import javax.inject.Inject

class SubRedditActivity : BaseActivity(), SubRedditContract.View {

    private lateinit var mBinding: SubRedditActivityBinding

    lateinit var mPresenter: SubRedditContract.Presenter<SubRedditContract.View>
        @Inject set

    lateinit var mAdapter: SubRedditAdapter
        @Inject set

    companion object {
        fun startActivity(context: Context): Intent {
            return Intent(context, SubRedditActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.sub_reddit_activity)

        mBinding.view = this

        activityComponent.inject(this)

        mPresenter.onAttach(this)
    }

    override fun initToolBar() {
        setSupportActionBar(mBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun setUpRecyclerView() {
        mBinding.recyclerView.run {
            if (adapter == null) {
                layoutManager = LinearLayoutManager(this@SubRedditActivity)
                adapter = mAdapter
            } else {
                mAdapter.notifyDataSetChanged()
            }
            visibility = View.VISIBLE
            hideProgressBar()
        }
    }

    override fun getFeed(view: View) {
        if (mBinding.etSearch.text.isNotEmpty())
            mPresenter.getSubRedditData(mBinding.etSearch.text.toString())
        else
            showToast("Please enter subreddit")
    }

    override fun hideProgressBar() {
        mBinding.progressBar.visibility = View.GONE
    }

    override fun showProgressBar() {
        mBinding.progressBar.visibility = View.VISIBLE
    }

    override fun launchCommentsActivity(commentData: CommentData) {
        startActivity(CommentActivity.startActivity(this, commentData))
    }
}