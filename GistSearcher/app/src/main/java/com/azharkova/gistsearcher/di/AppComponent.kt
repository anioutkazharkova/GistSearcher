package com.azharkova.gistsearcher.di

import android.content.Context
import com.azharkova.gistsearcher.GistSearchApplication
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(
    NetworkModule::class,
    ContextModule::class
))
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun withContext(context: Context): Builder
        fun build(): AppComponent
    }

    fun inject(app: GistSearchApplication)
}