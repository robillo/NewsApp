package com.assistiveapps.myapplication.di.component

import com.assistiveapps.myapplication.di.module.ViewModelFactoryModule
import com.assistiveapps.myapplication.di.module.ViewModelModule
import com.assistiveapps.myapplication.di.scope.PerFragmentScope
import com.assistiveapps.myapplication.ui.news_list.NewsListActivity
import dagger.Component

@PerFragmentScope
@Component(modules = [ViewModelFactoryModule::class, ViewModelModule::class], dependencies = [NewsAppComponent::class])
interface NewsListActivityComponent {
    fun injectNewsListActivity(newsListActivity: NewsListActivity)
}