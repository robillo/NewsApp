package com.assistiveapps.myapplication.di.module

import com.assistiveapps.myapplication.data.network.NewsRepository
import com.assistiveapps.myapplication.data.network.NewsService
import com.assistiveapps.myapplication.di.scope.NewsApplicationScope
import dagger.Module
import dagger.Provides

@Module(includes = [NewsServiceModule::class])
class NewsApiModule {

    @Provides
    @NewsApplicationScope
    fun newsApi(newsService: NewsService): NewsRepository {
        return NewsRepository(newsService)
    }
}
