package com.assistiveapps.myapplication.ui.news_detail

import android.content.Context
import android.content.Intent
import com.assistiveapps.myapplication.R
import com.assistiveapps.myapplication.ui.base.BaseActivity

class NewsDetailActivity : BaseActivity() {

    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, NewsDetailActivity::class.java)
            return intent
        }
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_news_detail
    }

    override fun setup() {
    }

    override fun onBackPressed() {
        super.onBackPressed()
        animateActivityTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}
