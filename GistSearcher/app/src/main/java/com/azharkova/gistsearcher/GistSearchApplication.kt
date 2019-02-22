package com.azharkova.gistsearcher

import android.app.Activity
import android.app.Application
import android.app.Service
import android.content.Context
import android.os.Build
import com.azharkova.gistsearcher.di.AppInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class GistSearchApplication: Application(), HasActivityInjector {

    companion object {
        lateinit var INSTANCE: GistSearchApplication
        var AppContext: Context? = null
    }

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>
    override fun activityInjector() = dispatchingActivityInjector

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        AppInjector.inject(this)
        AppContext = this
    }
}