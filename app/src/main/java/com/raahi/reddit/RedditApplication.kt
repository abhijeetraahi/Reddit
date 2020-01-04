package com.raahi.reddit

import android.app.Application
import com.raahi.reddit.di.components.ApplicationComponent
import com.raahi.reddit.di.components.DaggerApplicationComponent
import com.raahi.reddit.di.modules.ApplicationModule

class RedditApplication: Application() {

    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        appComponent =
            DaggerApplicationComponent.builder().applicationModule(ApplicationModule(this)).build()
    }
}