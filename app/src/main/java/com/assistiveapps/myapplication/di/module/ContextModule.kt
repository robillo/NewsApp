package com.assistiveapps.myapplication.di.module

import android.content.Context
import com.assistiveapps.myapplication.di.scope.NewsApplicationScope
import dagger.Module
import dagger.Provides

@Module
class ContextModule(val context: Context) {

    @Provides
    @NewsApplicationScope
    fun context(): Context {
        return context
    }
}