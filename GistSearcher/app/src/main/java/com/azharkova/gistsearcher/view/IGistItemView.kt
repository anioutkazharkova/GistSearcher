package com.azharkova.gistsearcher.view

import com.arellomobile.mvp.MvpView
import com.azharkova.gistsearcher.data.GistCommitInfo
import com.azharkova.gistsearcher.data.GistInfo

interface IGistItemView:MvpView {
    fun setupGist(item:GistInfo)
    fun setupCommits(commits: List<GistCommitInfo>)
}