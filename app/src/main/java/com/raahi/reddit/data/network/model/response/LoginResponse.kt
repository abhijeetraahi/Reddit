package com.raahi.reddit.data.network.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LoginResponse {

    @SerializedName("json")
    @Expose
    var json: Json? = null

    inner class Json{
        @SerializedName("errors")
        @Expose
        val errors: List<Any>? = null
        @SerializedName("data")
        @Expose
        val data: Data? = null
    }

    inner class Data{
        @SerializedName("modhash")
        @Expose
        val modhash: String? = null
        @SerializedName("cookie")
        @Expose
        val cookie: String? = null
    }
}