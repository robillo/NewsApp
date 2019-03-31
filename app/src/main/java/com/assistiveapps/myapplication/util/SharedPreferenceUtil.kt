package com.assistiveapps.myapplication.util

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.annotation.WorkerThread
import com.assistiveapps.myapplication.data.model.News
import com.google.gson.Gson

class SharedPreferenceUtil(
        context: Context,
        private val gson: Gson) {

    private val preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    private val editor: SharedPreferences.Editor = preferences.edit()

    fun getBoolean(key: String, value: Boolean): Boolean {
        return preferences.getBoolean(key, value)
    }

    fun getBoolean(key: String): Boolean {
        return getBoolean(key, false)
    }

    fun getFloat(key: String, value: Float): Float {
        return preferences.getFloat(key, value)
    }

    fun getFloat(key: String): Float {
        return getFloat(key, 0f)
    }

    fun getInt(key: String, value: Int): Int {
        return preferences.getInt(key, value)
    }

    fun getInt(key: String): Int {
        return getInt(key, 0)
    }

    fun getLong(key: String, value: Long): Long {
        return preferences.getLong(key, value)
    }

    fun getLong(key: String): Long {
        return getLong(key, 0L)
    }

    fun getString(key: String, default: String): String {
        return preferences.getString(key, default)
    }

    fun getString(key: String): String {
        return getString(key, "")
    }

    fun putBoolean(key: String, value: Boolean) {
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun putFloat(key: String, value: Float) {
        editor.putFloat(key, value)
        editor.apply()
    }

    fun putInt(key: String, value: Int) {
        editor.putInt(key, value)
        editor.apply()
    }

    fun putLong(key: String, value: Long) {
        editor.putLong(key, value)
        editor.apply()
    }

    @WorkerThread
    suspend fun putString(key: String, value: String) {
        editor.putString(key, value)
        editor.apply()
    }

    @WorkerThread
    suspend fun getNews(): News {
        return gson.fromJson(getString(Constants.EXTRA_NEWS), News::class.java)
    }

    fun clearAll() {
        editor.clear().commit()
    }

    fun clear(key: String) {
        editor.remove(key).apply()
    }
}