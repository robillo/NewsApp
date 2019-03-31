package com.assistiveapps.myapplication.ui.news_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.assistiveapps.myapplication.data.model.Error
import com.assistiveapps.myapplication.data.model.News
import com.assistiveapps.myapplication.data.network.NewsRepository
import com.assistiveapps.myapplication.util.Constants
import javax.inject.Inject

class NewsListActivityViewModel @Inject constructor(
        private val newsRepository: NewsRepository) : ViewModel(), NewsRepository.GetHeadlinesListener {

    private val _networkErrorLiveData = MutableLiveData<Error>()
    val networkErrorLiveData: LiveData<Error>
        get() = _networkErrorLiveData

    private val _newsListLiveData = MutableLiveData<List<News>>()
    val newsListLiveData: LiveData<List<News>>
        get() = _newsListLiveData

    fun getHeadlines() {
        newsRepository.getNews(Constants.API_KEY, this)
    }

    override fun onHeadlinesReceived(articles: List<News>?) {
        _newsListLiveData.postValue(articles)
    }

    override fun onHeadlinesFailure(error: Error) {
        _networkErrorLiveData.postValue(error)
    }

}