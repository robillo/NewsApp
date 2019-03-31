package com.assistiveapps.myapplication.ui.news_detail

import android.content.Context
import android.content.Intent
import com.assistiveapps.myapplication.R
import com.assistiveapps.myapplication.data.model.News
import com.assistiveapps.myapplication.ui.base.BaseActivity
import com.assistiveapps.myapplication.util.Constants
import com.assistiveapps.myapplication.util.StringUtils
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_news_detail.*
import android.net.Uri


class NewsDetailActivity : BaseActivity() {

    private lateinit var news: News

    companion object {
        fun newIntent(context: Context, newsString: String): Intent {
            val intent = Intent(context, NewsDetailActivity::class.java)
            intent.putExtra(Constants.EXTRA_NEWS, newsString)
            return intent
        }
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_news_detail
    }

    override fun setup() {
        setStatusBarColor(R.color.almost_white)
        getNewsDetails()
        setDetailsToViews()
        setClickListeners()
    }

    private fun getNewsDetails() {
        val newsString = intent.getStringExtra(Constants.EXTRA_NEWS)
        news = Gson().fromJson(newsString, News::class.java)
    }

    private fun setDetailsToViews() {
        Picasso.get().load(news.articleImage).into(newsImageView)

        titleTextView.text = news.title
        contentTextView.text = news.content
        shortDescriptionTextView.text = news.shortDescription
        newsTimeTextView.text = StringUtils.getTimeFromString(news.dateTime)
        newsDateTextView.text = StringUtils.getDateFromString(news.dateTime)
    }

    private fun setClickListeners() {
        readMore.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(news.fullArticleUrl)
            startActivity(i)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        animateActivityTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}
