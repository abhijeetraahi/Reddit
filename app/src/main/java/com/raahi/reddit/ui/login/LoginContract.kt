package com.raahi.reddit.ui.login

import com.raahi.reddit.ui.base.BaseContract

interface LoginContract {

    interface View: BaseContract.View{

        fun login(view: android.view.View)

        fun hideProgressBar()

        fun showProgressBar()

        fun launchHomeActivity()

    }

    interface Presenter<V: View>: BaseContract.Presenter<V>{

        fun login(userName: String, password: String)

        fun saveUserDetails(userName: String)

    }
}