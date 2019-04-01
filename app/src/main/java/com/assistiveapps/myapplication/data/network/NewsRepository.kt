package com.assistiveapps.myapplication.data.network
import com.assistiveapps.myapplication.data.model.News
import com.assistiveapps.myapplication.data.model.NewsResult
import com.assistiveapps.myapplication.util.ApiCallback
import com.assistiveapps.myapplication.util.Constants

class NewsRepository(private val newsService: NewsService) {

    fun getNews(apiKey: String, getHeadlinesListener: GetHeadlinesListener) {

        newsService.headlinesList(Constants.COUNTRY, apiKey).enqueue(object : ApiCallback<NewsResult>() {
            override fun success(response: NewsResult) {
                getHeadlinesListener.onHeadlinesReceived(response.articles)
            }

            override fun failure(error: Error) {
                getHeadlinesListener.onHeadlinesFailure(error)
            }
        })
    }

    interface GetHeadlinesListener {

        fun onHeadlinesReceived(articles: List<News>?)

        fun onHeadlinesFailure(error: Error)
    }
}