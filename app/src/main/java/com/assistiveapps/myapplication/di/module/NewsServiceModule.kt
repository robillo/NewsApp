package com.assistiveapps.myapplication.di.module

import com.assistiveapps.myapplication.data.network.NewsService
import com.assistiveapps.myapplication.di.scope.NewsApplicationScope
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module(includes = [NetworkModule::class])
class NewsServiceModule {

    companion object {
        private const val BASE_URL = "https://newsapi.org/"
    }

    @Provides
    @NewsApplicationScope
    fun newsService(retrofit: Retrofit): NewsService {
        return retrofit.create(NewsService::class.java)
    }

    @Provides
    @NewsApplicationScope
    fun retrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .build()
    }

    @Provides
    @NewsApplicationScope
    fun gson(): Gson {
        return GsonBuilder().create()
    }
}