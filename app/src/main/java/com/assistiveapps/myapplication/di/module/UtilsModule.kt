package com.assistiveapps.myapplication.di.module

import android.content.Context
import com.assistiveapps.myapplication.di.scope.NewsApplicationScope
import com.assistiveapps.myapplication.util.NetworkUtil
import dagger.Module
import dagger.Provides

@Module(includes = [ContextModule::class])
class UtilsModule {

    @Provides
    @NewsApplicationScope
    fun networkUtil(context: Context): NetworkUtil {
        return NetworkUtil(context)
    }
}