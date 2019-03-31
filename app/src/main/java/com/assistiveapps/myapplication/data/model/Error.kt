package com.assistiveapps.myapplication.data.model

import com.google.gson.annotations.SerializedName

data class Error(

        @SerializedName("message")
        val message: String = "Some Error Occurred"
)