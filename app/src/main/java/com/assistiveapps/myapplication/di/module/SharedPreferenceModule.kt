package com.assistiveapps.myapplication.di.module

import android.content.Context
import com.assistiveapps.myapplication.di.scope.NewsApplicationScope
import com.assistiveapps.myapplication.util.SharedPreferenceUtil
import com.google.gson.Gson
import dagger.Module
import dagger.Provides

@Module(includes = [ContextModule::class, NetworkModule::class])
class SharedPreferenceModule {

    @Provides
    @NewsApplicationScope
    fun sharedPreferenceUtil(context: Context, gson: Gson): SharedPreferenceUtil {
        return SharedPreferenceUtil(context, gson)
    }
}