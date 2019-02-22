package com.azharkova.gistsearcher.presentation

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.azharkova.gistsearcher.data.GistInfo
import com.azharkova.gistsearcher.data.GistUser
import com.azharkova.gistsearcher.services.repository.GistRepository
import com.azharkova.gistsearcher.view.IGistListView
import javax.inject.Inject

@InjectViewState
class GistListPresenter @Inject constructor(private  val repository: GistRepository):MvpPresenter<IGistListView>() {

    private var gists : ArrayList<GistInfo> = arrayListOf()
    private  var topUsersData : ArrayList<Pair<GistUser,List<GistInfo>>> = arrayListOf()

    private val take = 3000
    private val topCount = 10

    fun getGists(){
        repository.getGistList(take=take).subscribe({
            val loadedGists = it ?: arrayListOf()
            gists.addAll(loadedGists)
            viewState?.setupGistsList(gists)
            processUsers()
        },{

        })
    }

    private  fun processUsers(){
        var usersSorted = gists.groupBy { it.owner }.toList().sortedBy { it.second.size }.reversed()
        topUsersData.addAll( if (usersSorted.size < topCount) usersSorted else usersSorted.subList(0,topCount))

        viewState?.setupUsers(topUsersData.map { it.first })
    }

    fun selectGist(index: Int) {
        viewState?.openGist(gists[index])
    }

    fun selectUser(index: Int){
        viewState?.openUserGists(topUsersData[index].second
            ,topUsersData[index].first)
    }


}