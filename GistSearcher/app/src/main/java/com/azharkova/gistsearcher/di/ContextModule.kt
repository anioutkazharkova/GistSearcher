package com.azharkova.gistsearcher.di

import com.azharkova.gistsearcher.view.GistItemActivity
import com.azharkova.gistsearcher.view.GistListActivity
import com.azharkova.gistsearcher.view.GistUserActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Module(includes = [AndroidSupportInjectionModule::class])
abstract class ContextModule {

    @ContributesAndroidInjector
    abstract fun gistListActivityInjector(): GistListActivity

    @ContributesAndroidInjector
    abstract fun gistItemActivityInjector(): GistItemActivity

    @ContributesAndroidInjector
    abstract fun gistUserActivityInjector(): GistUserActivity
}