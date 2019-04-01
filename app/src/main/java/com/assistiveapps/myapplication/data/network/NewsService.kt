package com.assistiveapps.myapplication.data.network

import com.assistiveapps.myapplication.data.model.NewsResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("/v2/top-headlines")
    fun headlinesList(@Query("country") country: String, @Query("apiKey") apiKey: String): Call<NewsResult>
}