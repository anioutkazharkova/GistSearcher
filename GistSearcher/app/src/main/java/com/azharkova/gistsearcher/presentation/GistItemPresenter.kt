package com.azharkova.gistsearcher.presentation

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.azharkova.gistsearcher.data.GistCommitInfo
import com.azharkova.gistsearcher.data.GistInfo
import com.azharkova.gistsearcher.services.repository.GistRepository
import com.azharkova.gistsearcher.view.IGistItemView
import javax.inject.Inject

@InjectViewState
class GistItemPresenter @Inject constructor(private val repository: GistRepository):MvpPresenter<IGistItemView>() {
    private var gistItem: GistInfo? = null
    private var commits: ArrayList<GistCommitInfo> = arrayListOf()

    fun setupGist(item: GistInfo) {
        this.gistItem = item
        this.gistItem?.let {
            viewState?.setupGist(it)
            loadGist(it.id)
        }
    }

    fun loadGist(id: String) {
        repository.getGistById(id).subscribe({
            //NOTE the data received via this method is not equal to the list item
            //It differs in files
            val gist = it
            if (gist != null) {
                this.gistItem = gist
                viewState?.setupGist(gist!!)
                loadGistCommits()
            }
        }, {})
    }

    fun loadGistCommits(){
        this.gistItem?.id?.let {
            repository.getGistCommits(it).subscribe({
val loadedCommits = it ?: arrayListOf()
                commits.addAll(loadedCommits)
                viewState?.setupCommits(commits)
            },{})
        }
    }
}