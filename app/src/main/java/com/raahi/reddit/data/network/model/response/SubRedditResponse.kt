package com.raahi.reddit.data.network.model.response

import com.google.gson.annotations.SerializedName
import com.raahi.reddit.utils.Util

class SubRedditResponse {

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
        val data: DataChild? = null
    }

    inner class DataChild {
        @SerializedName("subreddit")
        var subreddit: String? = null
        @SerializedName("title")
        val title: String? = null
        @SerializedName("name")
        val name: String? = null
        @SerializedName("author")
        val author: String? = null
        @SerializedName("date")
        val date: String? = null
        @SerializedName("thumbnail")
        val thumbnail: String? = null
        @SerializedName("num_comments")
        val num_comments: Int = 0
        @SerializedName("created_utc")
        val created_utc: Long = 0
        @SerializedName("permalink")
        val permalink: String? = null
        @SerializedName("id")
        val id: String? = null

        fun getLocalDate(): String{
            return Util.convertUtc2Local(created_utc.toString())!!
        }

        fun getCommentsCount(): String{
            return "$num_comments Comments"
        }
    }
}