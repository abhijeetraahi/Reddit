package com.raahi.reddit.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.raahi.reddit.R
import com.raahi.reddit.data.network.model.CommentData
import com.raahi.reddit.databinding.HomeActivityBinding
import com.raahi.reddit.ui.base.BaseActivity
import com.raahi.reddit.ui.comment.CommentActivity
import com.raahi.reddit.ui.home.adapter.HomeAdapter
import com.raahi.reddit.ui.subreddit.SubRedditActivity
import com.raahi.reddit.utils.RedditBottomDialogFragment
import javax.inject.Inject

class HomeActivity : BaseActivity(), HomeContract.View, RedditBottomDialogFragment.ItemClickListener{

    private lateinit var mBinding: HomeActivityBinding

    lateinit var mPresenter: HomeContract.Presenter<HomeContract.View>
        @Inject set

    lateinit var mAdapter: HomeAdapter
        @Inject set

    companion object {
        fun startActivity(context: Context): Intent {
            return Intent(context, HomeActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.home_activity)

        mBinding.view = this

        activityComponent.inject(this)

        mPresenter.onAttach(this)

    }

    override fun initToolBar() {
        setSupportActionBar(mBinding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun setUpRecyclerView() {

        mBinding.recyclerView.run {
            if (adapter == null) {
                layoutManager = LinearLayoutManager(this@HomeActivity)
                adapter = mAdapter
            } else {
                mAdapter.notifyDataSetChanged()
            }
            visibility = View.VISIBLE
            hideProgressBar()
        }
    }

    override fun launchSubRedditActivity(view: View) {
        startActivity(SubRedditActivity.startActivity(this))
    }

    override fun hideProgressBar() {
        mBinding.progressBar.visibility = View.GONE
    }

    override fun showProgressBar() {
        mBinding.progressBar.visibility = View.VISIBLE
    }

    override fun showBottomSheet(view: View) {
        val addPhotoBottomDialogFragment: RedditBottomDialogFragment =
            RedditBottomDialogFragment.newInstance()!!
        addPhotoBottomDialogFragment.show(
            supportFragmentManager,
            RedditBottomDialogFragment.TAG
        )
    }

    override fun launchCommentsActivity(commentData: CommentData) {
        startActivity(CommentActivity.startActivity(this, commentData))
    }

    override fun onItemClick(item: String?) {
        mBinding.tvFilterType.text = (item?.toUpperCase() + " POSTS")

        when(item){
            R.string.best.toString() -> mBinding.tvFilterType.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_best, 0, 0, 0)
            R.string.hot.toString() -> mBinding.tvFilterType.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_hot, 0, 0, 0)
            R.string.new_post.toString() -> mBinding.tvFilterType.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_new, 0, 0, 0)
            R.string.top.toString() -> mBinding.tvFilterType.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_top, 0, 0, 0)
        }

        item?.toLowerCase()?.let { mPresenter.getHomeData(it) }
    }
}