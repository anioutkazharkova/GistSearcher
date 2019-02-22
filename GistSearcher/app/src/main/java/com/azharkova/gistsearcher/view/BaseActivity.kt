package com.azharkova.gistsearcher.view

import android.app.ActionBar
import android.app.Activity
import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import dagger.android.AndroidInjection

abstract class BaseActivity : MvpAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return  true
    }
}