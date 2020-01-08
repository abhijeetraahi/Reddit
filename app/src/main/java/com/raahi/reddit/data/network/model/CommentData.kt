package com.raahi.reddit.data.network.model

import com.raahi.reddit.utils.Util
import java.io.Serializable

data class CommentData(var subReddit: String, var author: String, var createdUTC: String, var title: String, var thumbnail: String, var permalink: String, var id: String): Serializable {

    fun getCreatedDate(): String{
        return Util.convertUtc2Local(createdUTC)!!
    }
}