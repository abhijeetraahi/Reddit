package com.raahi.reddit.di.modules

import androidx.appcompat.app.AppCompatActivity
import com.raahi.reddit.data.network.ApiHelper
import com.raahi.reddit.data.network.RedditApiHelper
import com.raahi.reddit.di.ActivityScope
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
}
