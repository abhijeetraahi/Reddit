package com.raahi.reddit.di.components

import android.app.Application
import android.content.Context
import com.raahi.reddit.RedditApplication
import com.raahi.reddit.data.DataManager
import com.raahi.reddit.di.ApplicationContext
import com.raahi.reddit.di.modules.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: RedditApplication)

    @ApplicationContext
    fun context(): Context

    fun application(): Application

    fun dataManager(): DataManager
}
