package com.assistiveapps.myapplication.data.model

import com.google.gson.annotations.SerializedName

data class NewsResult(
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int,
    @SerializedName("articles")
    val articles: List<News>
)