package com.assistiveapps.myapplication.ui.splash

import android.os.Handler
import com.assistiveapps.myapplication.R
import com.assistiveapps.myapplication.ui.base.BaseActivity
import com.assistiveapps.myapplication.ui.news_list.NewsListActivity

class SplashActivity : BaseActivity() {

    companion object {
        const val SPLASH_TIMEOUT = 1000L
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_splash
    }

    override fun setup() {
        launchNewsListActivity()
    }

    private fun launchNewsListActivity() {
        Handler().postDelayed({
            startActivity(NewsListActivity.newIntent(this))
            animateActivityTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            finishAffinity()
        }, SPLASH_TIMEOUT)
    }
}
