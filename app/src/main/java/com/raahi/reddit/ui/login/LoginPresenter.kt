package com.raahi.reddit.ui.login

import com.raahi.reddit.data.DataManager
import com.raahi.reddit.data.network.model.response.LoginResponse
import com.raahi.reddit.ui.base.BasePresenter
import com.raahi.reddit.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class LoginPresenter<V : LoginContract.View> @Inject constructor(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable
) : BasePresenter<V>(dataManager, schedulerProvider, compositeDisposable),
    LoginContract.Presenter<V> {

    private lateinit var mResponse: LoginResponse

    override fun login(userName: String, password: String) {
        view?.showProgressBar()
        compositeDisposable.add(
            dataManager.login(userName,userName,password,"json")
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({ response ->
                    mResponse = response
                    view?.hideProgressBar()

                    if (!mResponse.json?.data?.modhash.isNullOrEmpty()){
                        saveUserDetails(userName)
                        view?.showToast("success")
                        view?.launchHomeActivity()
                    }
                    else{
                        view?.showToast("Incorrect username and password")
                    }
                }, {
                    view?.hideProgressBar()
                    it.message?.let { message -> view?.showToast(message) }
                })
        )
    }

    override fun saveUserDetails(userName: String) {
        dataManager.saveUserName(userName)
        mResponse.json?.data?.modhash?.let { dataManager.saveModhash(it) }
        mResponse.json?.data?.cookie?.let { dataManager.saveCookie(it) }
    }
}