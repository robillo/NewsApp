package com.assistiveapps.myapplication.di.module

import androidx.lifecycle.ViewModel
import com.assistiveapps.myapplication.di.mapkey.ViewModelKey
import com.assistiveapps.myapplication.ui.news_list.NewsListActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(NewsListActivityViewModel::class)
    abstract fun bindExamViewModel(newsListActivityViewModel: NewsListActivityViewModel): ViewModel

}