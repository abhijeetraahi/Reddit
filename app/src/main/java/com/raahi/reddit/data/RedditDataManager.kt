package com.raahi.reddit.data

import android.app.Application
import com.raahi.reddit.data.network.ApiHelper
import com.raahi.reddit.data.network.model.response.*
import com.raahi.reddit.data.preference.RedditPreferenceHelper
import io.reactivex.Observable
import javax.inject.Inject

class RedditDataManager @Inject constructor(context: Application, apiHelper: ApiHelper) : DataManager {

    private var mApiHelper = apiHelper
    private var mPreferenceHelper = RedditPreferenceHelper(context)


    override fun login(
        username: String,
        user: String,
        password: String,
        type: String
    ): Observable<LoginResponse> {
        return mApiHelper.login(username, user, password, type)
    }

    override fun getHomeResponse(filterType: String, limit: Int): Observable<HomeResponse> {
        return mApiHelper.getHomeResponse(filterType, limit)
    }

    override fun getSubRedditResponse(subReddit: String): Observable<SubRedditResponse> {
        return mApiHelper.getSubRedditResponse(subReddit)
    }

    override fun getAllComments(permalink: String): Observable<List<CommentResponse>> {
        return mApiHelper.getAllComments(permalink)
    }

    override fun postComment(
        headers: Map<String, String>,
        path: String,
        parent: String,
        text: String
    ): Observable<GenericesResponse> {
        return mApiHelper.postComment(headers, path, parent, text)
    }

    override fun vote(
        headers: Map<String, String>,
        dir: Int,
        rank: Int,
        id: String
    ): Observable<EmptyResponse> {
        return mApiHelper.vote(headers, dir, rank, id)
    }


    override fun saveUserName(name: String) {
        mPreferenceHelper.saveUserName(name)
    }

    override fun saveModhash(modhash: String) {
        mPreferenceHelper.saveModhash(modhash)
    }

    override fun saveCookie(cookie: String) {
        mPreferenceHelper.saveCookie(cookie)
    }

    override fun getUserName(): String {
        return mPreferenceHelper.getUserName()
    }

    override fun getModhash(): String {
        return mPreferenceHelper.getModhash()
    }

    override fun getCookie(): String {
        return mPreferenceHelper.getCookie()
    }
}
