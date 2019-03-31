package com.assistiveapps.myapplication.di.module

import androidx.lifecycle.ViewModelProvider
import com.assistiveapps.myapplication.util.DaggerViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: DaggerViewModelFactory): ViewModelProvider.Factory
}