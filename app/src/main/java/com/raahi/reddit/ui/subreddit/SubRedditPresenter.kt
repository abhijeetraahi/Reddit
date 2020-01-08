package com.raahi.reddit.ui.subreddit

import com.raahi.reddit.data.DataManager
import com.raahi.reddit.data.network.model.CommentData
import com.raahi.reddit.data.network.model.response.SubRedditResponse
import com.raahi.reddit.ui.base.BasePresenter
import com.raahi.reddit.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SubRedditPresenter<V : SubRedditContract.View> @Inject constructor(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable
) : BasePresenter<V>(dataManager, schedulerProvider, compositeDisposable),
    SubRedditContract.Presenter<V> {

    private lateinit var mResponse: SubRedditResponse

    override fun onAttach(view: V) {
        super.onAttach(view)
        view.initToolBar()
    }

    override fun getSubRedditData(subReddit: String) {

        view?.showProgressBar()

        compositeDisposable.add(
            dataManager.getSubRedditResponse(subReddit)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({ response ->
                    mResponse = response
                    view?.hideProgressBar()

                    if (mResponse.data?.children?.isNotEmpty()!!)
                        view?.setUpRecyclerView()

                }, {
                    view?.hideProgressBar()
                    it.message?.let { message -> view?.showToast(message) }
                })
        )
    }

    override fun getSubRedditResponse(): SubRedditResponse {
        return mResponse
    }

    override fun onCommentClicked(position: Int) {

        val commentData =
            mResponse.data?.children?.get(position)?.data?.subreddit?.let {
                mResponse.data?.children?.get(position)?.data?.author?.let { it1 ->
                    mResponse.data?.children?.get(position)?.data?.title?.let { it2 ->
                        mResponse.data!!.children!![position].data?.thumbnail?.let { it3 ->
                            mResponse.data!!.children?.get(position)?.data?.permalink?.let { it4 ->
                                mResponse.data!!.children?.get(position)?.data?.name?.let { it5 ->
                                    CommentData(
                                        it, it1,
                                        mResponse.data?.children?.get(position)?.data?.created_utc.toString(),
                                        it2,
                                        it3,
                                        it4,
                                        it5
                                    )
                                }
                            }
                        }
                    }
                }
            }
        commentData?.let { view?.launchCommentsActivity(it) }
    }
}