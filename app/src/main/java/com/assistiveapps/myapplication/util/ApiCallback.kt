package com.assistiveapps.myapplication.util

import com.assistiveapps.myapplication.data.exception.NoConnectivityException
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response
import timber.log.Timber
import java.lang.Exception

abstract class ApiCallback<T> : retrofit2.Callback<T> {

    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (response.isSuccessful) {
            response.body()?.let { body ->
                success(body)
            } ?: run {
                createError(response)
            }
        } else {
            createError(response)
        }
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        Timber.e(t.cause)
        var error = Error()
        if (t is NoConnectivityException) {
            error = Error(t.message!!)
            failure(error)
        } else
            failure(error)
    }

    private fun createError(response: Response<T>) {
        response.errorBody()?.let { errorBody ->
            try {
                val error = Gson().fromJson<Error>(errorBody.string(), Error::class.java)
                failure(error)
            } catch (e: Exception) {
                failure(Error(e.localizedMessage))
            }
        } ?: run { failure(Error()) }
    }

    abstract fun success(response: T)

    abstract fun failure(error: Error)
}
