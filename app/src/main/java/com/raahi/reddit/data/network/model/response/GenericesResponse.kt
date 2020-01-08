package com.raahi.reddit.data.network.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GenericesResponse {

    @SerializedName("success")
    @Expose
    var success: String? = null

}