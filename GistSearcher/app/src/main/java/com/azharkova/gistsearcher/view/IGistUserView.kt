package com.azharkova.gistsearcher.view

import com.arellomobile.mvp.MvpView
import com.azharkova.gistsearcher.data.GistInfo
import com.azharkova.gistsearcher.data.GistUser

interface IGistUserView :MvpView {
    fun setupGistsList(data: List<GistInfo>)
    fun setupUser(user: GistUser)
}