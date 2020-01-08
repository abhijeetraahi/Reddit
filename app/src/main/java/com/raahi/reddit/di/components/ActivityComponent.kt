package com.raahi.reddit.di.components

import com.raahi.reddit.di.ActivityScope
import com.raahi.reddit.di.modules.ActivityModule
import com.raahi.reddit.ui.comment.CommentActivity
import com.raahi.reddit.ui.home.HomeActivity
import com.raahi.reddit.ui.login.LoginActivity
import com.raahi.reddit.ui.splash.SplashActivity
import com.raahi.reddit.ui.subreddit.SubRedditActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(loginActivity: LoginActivity)

    fun inject(homeActivity: HomeActivity)

    fun inject(splashActivity: SplashActivity)

    fun inject(subRedditActivity: SubRedditActivity)

    fun inject(commentActivity: CommentActivity)
}
