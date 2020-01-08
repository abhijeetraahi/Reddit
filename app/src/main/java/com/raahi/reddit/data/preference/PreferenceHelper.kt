package com.raahi.reddit.data.preference

interface PreferenceHelper {

    fun saveUserName(name: String)

    fun saveModhash(modhash: String)

    fun saveCookie(cookie: String)

    fun getUserName(): String

    fun getModhash(): String

    fun getCookie(): String
}