package com.raahi.reddit.data.network

import com.raahi.reddit.data.network.model.response.*
import io.reactivex.Observable
import retrofit2.Retrofit
import javax.inject.Inject

class RedditApiHelper @Inject constructor(retrofit: Retrofit): ApiHelper {

    private var mApiHelper: ApiHelper = retrofit.create<ApiHelper>(ApiHelper::class.java)

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

}