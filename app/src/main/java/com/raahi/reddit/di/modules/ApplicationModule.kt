package com.raahi.reddit.di.modules

import android.app.Application
import android.content.Context
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.raahi.reddit.BuildConfig
import com.raahi.reddit.RedditApplication
import com.raahi.reddit.data.DataManager
import com.raahi.reddit.data.RedditDataManager
import com.raahi.reddit.data.network.ApiHelper
import com.raahi.reddit.data.network.RedditApiHelper
import com.raahi.reddit.di.ApplicationContext
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: RedditApplication) {
    @Provides
    @ApplicationContext
    fun providesContext(): Context {
        return application
    }

    @Provides
    fun provideApplication(): Application {
        return application
    }

    @Provides
    @Singleton
    fun getOKHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                chain.proceed(
                    chain.request()
                        .newBuilder()
                        .build()
                )
            }
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun getRetrofit(defaultHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(defaultHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun getApiHelper(apiHelper: RedditApiHelper): ApiHelper {
        return apiHelper
    }

    @Provides
    @Singleton
    fun getDataManager(dataManager: RedditDataManager): DataManager {
        return dataManager
    }
}