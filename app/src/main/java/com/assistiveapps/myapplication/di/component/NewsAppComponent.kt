package com.assistiveapps.myapplication.di.component

import com.assistiveapps.myapplication.data.network.NewsRepository
import com.assistiveapps.myapplication.di.module.NewsApiModule
import com.assistiveapps.myapplication.di.module.PicassoModule
import com.assistiveapps.myapplication.di.module.SharedPreferenceModule
import com.assistiveapps.myapplication.di.module.UtilsModule
import com.assistiveapps.myapplication.di.scope.NewsApplicationScope
import com.assistiveapps.myapplication.util.SharedPreferenceUtil
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import dagger.Component

@NewsApplicationScope
@Component(modules = [
    NewsApiModule::class, PicassoModule::class,
    SharedPreferenceModule::class, UtilsModule::class
])
interface NewsAppComponent {

    fun getNewsRepository(): NewsRepository

    fun getSharedPreferenceUtil(): SharedPreferenceUtil

    fun getPicasso(): Picasso

    fun getGson(): Gson
}