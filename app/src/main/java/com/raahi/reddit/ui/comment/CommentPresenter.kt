package com.raahi.reddit.ui.comment

import android.util.Log
import com.raahi.reddit.BuildConfig
import com.raahi.reddit.data.DataManager
import com.raahi.reddit.data.network.model.CommentData
import com.raahi.reddit.data.network.model.response.CommentResponse
import com.raahi.reddit.ui.base.BasePresenter
import com.raahi.reddit.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import java.util.*
import javax.inject.Inject

class CommentPresenter<V : CommentContract.View> @Inject constructor(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable
) : BasePresenter<V>(dataManager, schedulerProvider, compositeDisposable),
    CommentContract.Presenter<V> {

    private lateinit var mCommentData: CommentData
    private lateinit var mResponse: List<CommentResponse>
    private var mCommentList = mutableListOf<CommentResponse.Child>()

    override fun onAttach(view: V) {
        super.onAttach(view)

        view.initToolBar()
        view.initView()
        getAllComments()
    }

    override fun setCommentData(commentData: CommentData) {
        mCommentData = commentData
    }

    override fun getCommentData(): CommentData {
        return mCommentData
    }

    override fun getAllComments() {
        view?.showProgressBar()

        compositeDisposable.add(
            dataManager.getAllComments(BuildConfig.BASE_URL + mCommentData.permalink + ".json")
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({ response ->

                    mResponse = response
                    view?.hideProgressBar()

                    if (mResponse.isNotEmpty())
                        view?.setUpRecyclerView()

                }, {
                    view?.hideProgressBar()
                    it.message?.let { message -> view?.showToast(message) }
                })
        )
    }

    override fun getCommentList(): ArrayList<CommentResponse.Child> {
        mCommentList.clear()
        for (response in mResponse) {
            if (response.data?.children?.isNotEmpty()!!)
                for (i in 0 until response.data.children.size)
                    response.data.children[i].data?.let { mCommentList.add(it) }
        }
        mCommentList.removeAt(0)
        return mCommentList as ArrayList<CommentResponse.Child>
    }

    override fun postComment(comment: String) {

        view?.showProgressBar()

        val headerMap = HashMap<String, String>()
        headerMap["User-Agent"] = dataManager.getUserName()
        headerMap["X-Modhash"] = dataManager.getModhash()
        headerMap["cookie"] = "reddit_session=" + dataManager.getCookie()
        headerMap["Content-Type"] = "application/json"

        Log.d("xxxxx", dataManager.getModhash() + "   " + mCommentData.id)
        compositeDisposable.add(
            dataManager.postComment(headerMap, "api/comment", mCommentData.id, comment)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({ response ->
                    if (response.success.equals("true"))
                        view?.showToast("Post successfully")
                    else
                        view?.showToast("Post Un-Successful")
                    view?.hideProgressBar()

                }, {
                    view?.hideProgressBar()
                    it.message?.let { message -> view?.showToast(message) }
                })
        )
    }
}