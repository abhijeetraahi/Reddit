package com.raahi.reddit.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.raahi.reddit.R
import com.raahi.reddit.databinding.LoginActivityBinding
import com.raahi.reddit.ui.base.BaseActivity
import com.raahi.reddit.ui.home.HomeActivity
import javax.inject.Inject

class LoginActivity : BaseActivity(), LoginContract.View {

    private lateinit var mBinding: LoginActivityBinding

    lateinit var mPresenter: LoginContract.Presenter<LoginContract.View>
        @Inject set

    companion object {
        fun startActivity(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.login_activity)
        mBinding.view = this

        activityComponent.inject(this)

        mPresenter.onAttach(this)

    }

    override fun login(view: View) {
        if (mBinding.etUserName.text.isNotEmpty() && mBinding.etPassword.text.isNotEmpty())
            mPresenter.login(
                mBinding.etUserName.text.toString(),
                mBinding.etPassword.text.toString()
            )
        else
            showToast("UserName and Password field can't be empty")
    }

    override fun hideProgressBar() {
        mBinding.progressBar.visibility = View.GONE
    }

    override fun showProgressBar() {
        mBinding.progressBar.visibility = View.VISIBLE
    }

    override fun launchHomeActivity() {
        startActivity(HomeActivity.startActivity(this))
    }

    override fun onDestroy() {
        mPresenter.onDetach()
        super.onDestroy()
    }
}