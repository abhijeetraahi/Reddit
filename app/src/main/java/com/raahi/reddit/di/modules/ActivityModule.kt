package com.raahi.reddit.di.modules

import androidx.appcompat.app.AppCompatActivity
import com.raahi.reddit.data.network.ApiHelper
import com.raahi.reddit.data.network.RedditApiHelper
import com.raahi.reddit.di.ActivityScope
import com.raahi.reddit.ui.comment.CommentContract
import com.raahi.reddit.ui.comment.CommentPresenter
import com.raahi.reddit.ui.comment.adapter.CommentAdapter
import com.raahi.reddit.ui.home.HomeContract
import com.raahi.reddit.ui.home.HomePresenter
import com.raahi.reddit.ui.home.adapter.HomeAdapter
import com.raahi.reddit.ui.login.LoginContract
import com.raahi.reddit.ui.login.LoginPresenter
import com.raahi.reddit.ui.splash.SplashContract
import com.raahi.reddit.ui.splash.SplashPresenter
import com.raahi.reddit.ui.subreddit.SubRedditContract
import com.raahi.reddit.ui.subreddit.SubRedditPresenter
import com.raahi.reddit.ui.subreddit.adapter.SubRedditAdapter
import com.raahi.reddit.utils.rx.RedditSchedulerProvider
import com.raahi.reddit.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ActivityModule(private val mActivity: AppCompatActivity) {

    @Provides
    @ActivityScope
    fun provideActivity(): AppCompatActivity {
        return mActivity
    }

    @Provides
    fun getSchedulerProvider(schedulerProvider: RedditSchedulerProvider): SchedulerProvider {
        return schedulerProvider
    }

    @Provides
    fun getCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    @Provides
    fun getApiHelper(apiHelper: RedditApiHelper): ApiHelper {
        return apiHelper
    }

    @Provides
    @ActivityScope
    fun getLoginPresenter(loginPresenter: LoginPresenter<LoginContract.View>): LoginContract.Presenter<LoginContract.View> {
        return loginPresenter
    }

    @Provides
    @ActivityScope
    fun getHomePresenter(homePresenter: HomePresenter<HomeContract.View>): HomeContract.Presenter<HomeContract.View> {
        return homePresenter
    }

    @Provides
    @ActivityScope
    fun getHomeAdapter(presenter: HomeContract.Presenter<HomeContract.View>): HomeAdapter {
        return HomeAdapter(presenter)
    }

    @Provides
    @ActivityScope
    fun getSplashPresenter(splashPresenter: SplashPresenter<SplashContract.View>): SplashContract.Presenter<SplashContract.View> {
        return splashPresenter
    }

    @Provides
    @ActivityScope
    fun getSubRedditPresenter(subRedditPresenter: SubRedditPresenter<SubRedditContract.View>): SubRedditContract.Presenter<SubRedditContract.View> {
        return subRedditPresenter
    }

    @Provides
    @ActivityScope
    fun getSubRedditAdapter(presenter: SubRedditContract.Presenter<SubRedditContract.View>): SubRedditAdapter {
        return SubRedditAdapter(presenter)
    }

    @Provides
    @ActivityScope
    fun getCommentPresenter(commentPresenter: CommentPresenter<CommentContract.View>): CommentContract.Presenter<CommentContract.View> {
        return commentPresenter
    }

    @Provides
    @ActivityScope
    fun getCommentAdapter(presenter: CommentContract.Presenter<CommentContract.View>): CommentAdapter {
        return CommentAdapter(presenter)
    }
}
