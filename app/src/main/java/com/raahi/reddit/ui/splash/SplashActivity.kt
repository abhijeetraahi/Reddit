package com.raahi.reddit.ui.splash

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.raahi.reddit.R
import com.raahi.reddit.databinding.SplashActivityBinding
import com.raahi.reddit.ui.base.BaseActivity
import com.raahi.reddit.ui.home.HomeActivity
import com.raahi.reddit.ui.login.LoginActivity
import javax.inject.Inject

class SplashActivity : BaseActivity(), SplashContract.View {

    private lateinit var mBinding: SplashActivityBinding

    lateinit var mPresenter: SplashContract.Presenter<SplashContract.View>
        @Inject set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.splash_activity)

        activityComponent.inject(this)

        mPresenter.onAttach(this)
    }

    override fun launchLoginActivity() {
        startActivity(LoginActivity.startActivity(this))
    }

    override fun launchHomeActivity() {
        startActivity(HomeActivity.startActivity(this))
    }

    override fun finishActivity() {
        this.finish()
    }
}