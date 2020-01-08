package com.raahi.reddit.ui.home

import com.raahi.reddit.data.DataManager
import com.raahi.reddit.data.network.model.CommentData
import com.raahi.reddit.data.network.model.response.HomeResponse
import com.raahi.reddit.ui.base.BasePresenter
import com.raahi.reddit.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.util.*
import javax.inject.Inject


class HomePresenter<V : HomeContract.View> @Inject constructor(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable
) : BasePresenter<V>(dataManager, schedulerProvider, compositeDisposable),
    HomeContract.Presenter<V> {

    private lateinit var mResponse: HomeResponse

    private val filterType = "best"

    override fun onAttach(view: V) {
        super.onAttach(view)

        view.initToolBar()

        getHomeData(filterType)
    }

    override fun getHomeData(filterType: String) {

        view?.showProgressBar()

        compositeDisposable.add(
            dataManager.getHomeResponse(filterType, 100)
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

    override fun getHomeResponse(): HomeResponse {
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

    override fun submiteVote(dir: Int, position: Int) {

        view?.showProgressBar()

        val headerMap = HashMap<String, String>()
        headerMap["User-Agent"] = dataManager.getUserName()
        headerMap["X-Modhash"] = dataManager.getModhash()
        headerMap["cookie"] = "reddit_session=" + dataManager.getCookie()
        headerMap["Content-Type"] = "application/json"

        /*val request = VoteRequest()
        request.dir = dir
        request.id = mResponse.data?.children?.get(position)?.data?.name!!
        request.rank = 2*/

        val requestBody: RequestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("dir", dir.toString())
            .addFormDataPart("rank", "2")
            .addFormDataPart("id", mResponse.data?.children?.get(position)?.data?.name!!)
            .build()

        compositeDisposable.add(
            dataManager.vote(headerMap, dir, 2, mResponse.data?.children?.get(position)?.data?.name!!)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({ response ->
                    view?.showToast("Vote has been submitted")

                }, {
                    view?.hideProgressBar()
                    it.message?.let { message -> view?.showToast(message) }
                })
        )
    }
}