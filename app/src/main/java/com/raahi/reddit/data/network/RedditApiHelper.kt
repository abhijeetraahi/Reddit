package com.raahi.reddit.data.network

import retrofit2.Retrofit
import javax.inject.Inject

class RedditApiHelper @Inject constructor(retrofit: Retrofit): ApiHelper {

    private var mApiHelper: ApiHelper = retrofit.create<ApiHelper>(ApiHelper::class.java)


}