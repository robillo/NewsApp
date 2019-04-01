package com.assistiveapps.myapplication.ui.news_list

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DiffUtil
import com.assistiveapps.myapplication.NewsApp
import com.assistiveapps.myapplication.R
import com.assistiveapps.myapplication.data.model.News
import com.assistiveapps.myapplication.di.component.DaggerNewsListActivityComponent
import com.assistiveapps.myapplication.ui.base.BaseActivity
import com.assistiveapps.myapplication.ui.news_detail.NewsDetailActivity
import com.assistiveapps.myapplication.ui.news_list.adapter.NewsAdapter
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_news_list.*
import javax.inject.Inject

class NewsListActivity : BaseActivity(), NewsAdapter.OnNewsClickedListener {

    @Inject
    lateinit var gson: Gson

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var newsListViewModel: NewsListActivityViewModel

    private val comparator = object : DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.dateTime == newItem.dateTime
        }

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem == newItem
        }
    }

    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, NewsListActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            return intent
        }
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_news_list
    }

    override fun setup() {
        setNewsAdapter()
        setComponent()
        getHeadlines()
        setObservers()
    }

    private fun setComponent() {
        val component = DaggerNewsListActivityComponent.builder()
                .newsAppComponent(NewsApp.get(this).newsAppComponent())
                .build()

        component.injectNewsListActivity(this)

        newsListViewModel =
                ViewModelProviders.of(this, viewModelFactory).get(NewsListActivityViewModel::class.java)
    }

    private fun getHeadlines() {
        loadingProgressBar.visibility = View.VISIBLE
        newsListViewModel.getHeadlines()
    }

    private fun setObservers() {
        newsListViewModel.newsListLiveData.observe(this, Observer {
            loadingProgressBar.visibility = View.GONE
            newsAdapter.submitList(it)
        })
        newsListViewModel.networkErrorLiveData.observe(this, Observer {
            showErrorSnackBar()
        })
    }

    private fun setNewsAdapter() {
        newsAdapter = NewsAdapter(comparator)
        newsAdapter.setOnNewsClickedListener(this)
        newsRecyclerView.adapter = newsAdapter
    }

    private fun retryGettingHeadlines() {
        newsListViewModel.getHeadlines()
    }

    override fun onNewsClicked(news: News) {
        startActivity(NewsDetailActivity.newIntent(this, gson.toJson(news)))
        animateActivityTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    private fun showErrorSnackBar() {
        failLayout.visibility = View.VISIBLE
        loadingProgressBar.visibility = View.GONE
        Snackbar.make(parentLayout, getString(R.string.some_error_occurred), Snackbar.LENGTH_INDEFINITE)
            .setAction(getString(R.string.retry)) {
                loadingProgressBar.visibility = View.VISIBLE
                failLayout.visibility = View.GONE
                retryGettingHeadlines()
            }.show()
    }
}
