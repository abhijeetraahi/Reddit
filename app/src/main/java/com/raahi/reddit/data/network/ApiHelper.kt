package com.raahi.reddit.data.network

import com.raahi.reddit.data.network.model.response.*
import io.reactivex.Observable
import retrofit2.http.*

interface ApiHelper {

    @POST("api/login/{user}")
    fun login(
        @Path("user") username: String,
        @Query("user") user: String,
        @Query("passwd") password: String,
        @Query("api_type") type: String
    ): Observable<LoginResponse>

    @GET("{filterType}/.json")
    fun getHomeResponse(
        @Path("filterType") filterType: String,
        @Query("limit") limit: Int
    ): Observable<HomeResponse>

    @GET("r/{subReddit}/.json")
    fun getSubRedditResponse(
        @Path("subReddit") subReddit: String
    ): Observable<SubRedditResponse>

    @GET
    fun getAllComments(
        @Url permalink: String
    ): Observable<List<CommentResponse>>

    @POST("{path}/.json")
    fun postComment(
        @HeaderMap headers: Map<String, String>,
        @Path("path") path: String,
        @Query("parent") parent: String,
        @Query("amp;text") text: String
    ): Observable<GenericesResponse>

    @FormUrlEncoded
    @POST("api/vote")
    fun vote(
        @HeaderMap headers: Map<String, String>,
        @Field("dir") dir: Int,
        @Field("rank") rank: Int,
        @Field("id") id: String
    ): Observable<EmptyResponse>
}