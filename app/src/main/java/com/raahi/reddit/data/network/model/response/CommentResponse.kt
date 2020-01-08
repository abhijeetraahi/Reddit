package com.raahi.reddit.data.network.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.raahi.reddit.utils.Util
import java.util.*

class CommentResponse {

    @SerializedName("data")
    val data: Data? = null

    inner class Data{
        @SerializedName("children")
        val children: ArrayList<Children>? = null
    }

    inner class Children{
        @SerializedName("kind")
        val kind: String? = null
        @SerializedName("data")
        val data: Child? = null
    }

    inner class Child{
        @SerializedName("subreddit")
        var subreddit: String? = null
        @SerializedName("author_fullname")
        @Expose
        val author: String? = null
        @SerializedName("body")
        @Expose
        val body: String? = null
        @SerializedName("created_utc")
        val created_utc: Long = 0

        fun getLocalDate(): String{
            return Util.convertUtc2Local(created_utc.toString())!!
        }
    }
}