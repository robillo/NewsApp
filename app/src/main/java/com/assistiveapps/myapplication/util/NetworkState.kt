package com.assistiveapps.myapplication.util

class NetworkState(val status: Status, val msg: String = "Some error occurred") {

    enum class Status {
        RUNNING,
        SUCCESS,
        FAILED
    }
}