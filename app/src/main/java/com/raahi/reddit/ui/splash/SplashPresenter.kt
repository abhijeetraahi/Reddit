package com.raahi.reddit.ui.splash

import com.raahi.reddit.data.DataManager
import com.raahi.reddit.ui.base.BasePresenter
import com.raahi.reddit.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SplashPresenter<V : SplashContract.View> @Inject constructor(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable
) : BasePresenter<V>(dataManager, schedulerProvider, compositeDisposable),
    SplashContract.Presenter<V> {

    override fun onAttach(view: V) {
        super.onAttach(view)
        hasUserLogedin()
    }

    override fun hasUserLogedin() {
        if (dataManager.getUserName().isNullOrEmpty())
            view?.launchLoginActivity()
        else
            view?.launchHomeActivity()

        view?.finishActivity()
    }
}