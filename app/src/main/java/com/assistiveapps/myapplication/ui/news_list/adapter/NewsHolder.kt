package com.assistiveapps.myapplication.ui.news_list.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.assistiveapps.myapplication.data.model.News
import com.assistiveapps.myapplication.util.StringUtils
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_news.view.*

class NewsHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private lateinit var onNewsClickedListener: OnNewsClickedListener

    fun setDetails(news: News, onNewsClickedListener: OnNewsClickedListener) {
        this.onNewsClickedListener = onNewsClickedListener
        initViewContent(news)
        setClickListeners(news)
    }

    private fun initViewContent(news: News) {
        itemView.newsTimeTextView.text = StringUtils.getTimeFromString(news.dateTime)
        itemView.newsDateTextView.text = StringUtils.getDateFromString(news.dateTime)
        itemView.titleTextView.text = news.title

        Picasso.get()
                .load(news.articleImage)
                .into(itemView.newsImageView)
    }

    private fun setClickListeners(news: News) {
        itemView.setOnClickListener {
            onNewsClickedListener.onNewsClicked(news)
        }
    }

    interface OnNewsClickedListener {
        fun onNewsClicked(news: News)
    }
}