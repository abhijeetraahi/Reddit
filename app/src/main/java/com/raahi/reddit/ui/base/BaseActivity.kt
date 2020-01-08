package com.raahi.reddit.ui.base

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.raahi.reddit.RedditApplication
import com.raahi.reddit.di.components.ActivityComponent
import com.raahi.reddit.di.components.DaggerActivityComponent
import com.raahi.reddit.di.modules.ActivityModule

abstract class BaseActivity: AppCompatActivity(), BaseContract.View {

    lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)

        activityComponent = DaggerActivityComponent.builder().activityModule(ActivityModule(this))
            .applicationComponent((application as RedditApplication).appComponent)
            .build()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun showToast(message: String, length: Int) {
        Toast.makeText(this, message, length).show()
    }
}