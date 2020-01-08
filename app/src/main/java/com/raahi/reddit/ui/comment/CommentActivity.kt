package com.raahi.reddit.ui.comment

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.raahi.reddit.R
import com.raahi.reddit.data.network.model.CommentData
import com.raahi.reddit.databinding.CommentActivityBinding
import com.raahi.reddit.ui.base.BaseActivity
import com.raahi.reddit.ui.comment.adapter.CommentAdapter
import com.squareup.picasso.Picasso
import javax.inject.Inject

class CommentActivity : BaseActivity(), CommentContract.View {

    private lateinit var mBinding: CommentActivityBinding

    lateinit var mPresenter: CommentContract.Presenter<CommentContract.View>
        @Inject set

    lateinit var mAdapter: CommentAdapter
        @Inject set

    companion object {

        private const val COMMENT_DATA = "comment_data"
        fun startActivity(context: Context, commentData: CommentData): Intent {
            val intent = Intent(context, CommentActivity::class.java)
            intent.putExtra(COMMENT_DATA, commentData)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.comment_activity)

        activityComponent.inject(this)

        mPresenter.setCommentData(intent.getSerializableExtra(COMMENT_DATA) as CommentData)

        mPresenter.onAttach(this)

    }

    override fun initToolBar() {
        setSupportActionBar(mBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Comments"
    }

    override fun initView() {
        mBinding.view = mPresenter.getCommentData()

        Picasso.get()
            .load(mPresenter.getCommentData().thumbnail)
            .into(mBinding.imageView)

        Picasso.get()
            .load(mPresenter.getCommentData().thumbnail)
            .into(mBinding.ivThumbnail)
    }

    override fun setUpRecyclerView() {
        mBinding.recyclerView.run {
            if (adapter == null) {
                layoutManager = LinearLayoutManager(this@CommentActivity)
                adapter = mAdapter
            } else {
                mAdapter.notifyDataSetChanged()
            }
            visibility = View.VISIBLE
            hideProgressBar()
        }
    }

    override fun hideProgressBar() {
        mBinding.progressView.visibility = View.GONE
    }

    override fun showProgressBar() {
        mBinding.progressView.visibility = View.VISIBLE
    }

    fun launchCommentDialog(view: View) {

        val dialog = Dialog(this)
        dialog.setTitle("dialog")
        dialog.setContentView(R.layout.comment_dialog)

        val width = (resources.displayMetrics.widthPixels * 0.95).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.6).toInt()

        dialog.window!!.setLayout(width, height)
        dialog.show()

        val btnPostComment = dialog.findViewById<Button>(R.id.btnPostComment)
        val comment = dialog.findViewById<EditText>(R.id.etComment)

        btnPostComment.setOnClickListener {
            if (comment.text.isNotEmpty()) {
                mPresenter.postComment(comment.text.toString())
                dialog.dismiss()
            } else
                showToast("Please enter your comment")
        }
    }
}