package com.assistiveapps.myapplication.di.module

import android.content.Context
import com.assistiveapps.myapplication.di.scope.NewsApplicationScope
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides

@Module(includes = [ContextModule::class])
class PicassoModule {

    @Provides
    @NewsApplicationScope
    fun picasso(context: Context): Picasso {
        return Picasso.Builder(context)
                .build()
    }

}