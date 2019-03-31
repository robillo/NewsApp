package com.assistiveapps.myapplication.data.network
import android.util.Log
import com.assistiveapps.myapplication.data.model.Error
import com.assistiveapps.myapplication.data.model.News
import com.assistiveapps.myapplication.data.model.NewsResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRepository(private val newsService: NewsService) {

    fun getNews(apiKey: String, getHeadlinesListener: GetHeadlinesListener) {
        newsService.headlinesList("us", apiKey).enqueue(object : Callback<NewsResult> {

            override fun onResponse(call: Call<NewsResult>, response: Response<NewsResult>) {
                getHeadlinesListener.onHeadlinesReceived(response.body()?.articles)
            }

            override fun onFailure(call: Call<NewsResult>, t: Throwable) {
                getHeadlinesListener.onHeadlinesFailure(Error("failed to fetch"))
            }
        })
    }

    interface GetHeadlinesListener {

        fun onHeadlinesReceived(articles: List<News>?)

        fun onHeadlinesFailure(error: Error)
    }
}