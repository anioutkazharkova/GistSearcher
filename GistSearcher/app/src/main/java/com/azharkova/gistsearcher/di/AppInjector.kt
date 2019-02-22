package com.azharkova.gistsearcher.di
import dagger.android.*
import com.azharkova.gistsearcher.GistSearchApplication

object AppInjector {

    fun inject(app: GistSearchApplication) {
        DaggerAppComponent.builder()
            .withContext(app)
            .build()
            .inject(app)
    }
}