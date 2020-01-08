package com.raahi.reddit.data.preference

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.raahi.reddit.R

class RedditPreferenceHelper constructor(context: Application): PreferenceHelper {

    private var mPref: SharedPreferences = context.getSharedPreferences(context.getString(R.string.REDDIT_PREFERENCE), Context.MODE_PRIVATE)

    companion object{
        private const val USER_NAME = "user_name"
        private const val MOD_HASH = "mod_hash"
        private const val COOKIE = "cookie"
    }

    override fun saveUserName(name: String) {
        mPref.edit().putString(USER_NAME, name).apply()
    }

    override fun saveModhash(modhash: String) {
        mPref.edit().putString(MOD_HASH, modhash).apply()
    }

    override fun saveCookie(cookie: String) {
        mPref.edit().putString(COOKIE, cookie).apply()
    }

    override fun getUserName(): String {
        return mPref.getString(USER_NAME, "")!!
    }

    override fun getModhash(): String {
        return mPref.getString(MOD_HASH, "")!!
    }

    override fun getCookie(): String {
        return mPref.getString(COOKIE, "")!!
    }
}