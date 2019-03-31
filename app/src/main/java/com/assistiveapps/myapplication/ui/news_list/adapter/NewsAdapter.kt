package com.assistiveapps.myapplication.ui.news_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.assistiveapps.myapplication.R
import com.assistiveapps.myapplication.data.model.News

class NewsAdapter(comparator: DiffUtil.ItemCallback<News>):
        ListAdapter<News, NewsHolder>(comparator), NewsHolder.OnNewsClickedListener {

    private lateinit var onNewsClickedListener: OnNewsClickedListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        return NewsHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.row_news, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        getItem(position)?.let {
            holder.setDetails(it, this)
        }
    }

    override fun onNewsClicked(news: News) {
        onNewsClickedListener.onNewsClicked(news)
    }

    fun setOnNewsClickedListener(onNewsClickedListener: OnNewsClickedListener) {
        this.onNewsClickedListener = onNewsClickedListener
    }

    interface OnNewsClickedListener {
        fun onNewsClicked(news: News)
    }
}