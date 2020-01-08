package com.raahi.reddit.ui.base

import com.raahi.reddit.data.DataManager
import com.raahi.reddit.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

open class BasePresenter<V : BaseContract.View> constructor(
    val dataManager: DataManager,
    val schedulerProvider: SchedulerProvider,
    val compositeDisposable: CompositeDisposable
) : BaseContract.Presenter<V> {

    var view: V? = null

    override fun onAttach(view: V) {
        this.view = view
    }

    override fun onDetach() {
        compositeDisposable.dispose()
        view = null
    }
}