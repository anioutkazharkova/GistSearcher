package com.azharkova.gistsearcher.presentation

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.azharkova.gistsearcher.data.GistInfo
import com.azharkova.gistsearcher.data.GistUser
import com.azharkova.gistsearcher.view.IGistUserView
import javax.inject.Inject

@InjectViewState
class GistUserPresenter @Inject constructor():MvpPresenter<IGistUserView>() {
    var user: GistUser? = null
    set(value) {
       value?.let {
           viewState?.setupUser(it)
       }
    }
    private var gists: ArrayList<GistInfo> = arrayListOf()

    fun setupGists(gists:List<GistInfo>?){
      this.gists.addAll(gists ?: arrayListOf())
        viewState?.setupGistsList(this.gists)
    }
}