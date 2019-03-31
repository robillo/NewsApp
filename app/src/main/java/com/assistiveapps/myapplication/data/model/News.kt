package com.assistiveapps.myapplication.data.model

import com.google.gson.annotations.SerializedName

data class News(
    @SerializedName("source")
    val source: Source,
    @SerializedName("author")
    val author: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val shortDescription: String,
    @SerializedName("url")
    val fullArticleUrl: String,
    @SerializedName("urlToImage")
    val articleImage: String,
    @SerializedName("publishedAt")
    val dateTime: String,
    @SerializedName("content")
    val content: String
)