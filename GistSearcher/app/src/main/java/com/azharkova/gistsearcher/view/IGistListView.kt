package com.azharkova.gistsearcher.view

import com.arellomobile.mvp.MvpView
import com.azharkova.gistsearcher.data.GistInfo
import com.azharkova.gistsearcher.data.GistUser

interface IGistListView :MvpView {
    fun setupGistsList(data: List<GistInfo>)
    fun setupUsers(users: List<GistUser>)
    fun openGist(item: GistInfo)
    fun openUserGists(gists: List<GistInfo>,user: GistUser)
}