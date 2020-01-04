package com.raahi.reddit.data

import com.raahi.reddit.data.network.ApiHelper
import javax.inject.Inject

class RedditDataManager @Inject constructor(apiHelper: ApiHelper): DataManager {

    private var mApiHelper = apiHelper

}
