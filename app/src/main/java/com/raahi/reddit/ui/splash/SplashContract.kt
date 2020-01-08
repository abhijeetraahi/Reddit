package com.raahi.reddit.ui.splash

import com.raahi.reddit.ui.base.BaseContract

interface SplashContract {

    interface View: BaseContract.View{

        fun launchLoginActivity()

        fun launchHomeActivity()

        fun finishActivity()

    }

    interface Presenter<V: View>: BaseContract.Presenter<V>{
        fun hasUserLogedin()
    }
}