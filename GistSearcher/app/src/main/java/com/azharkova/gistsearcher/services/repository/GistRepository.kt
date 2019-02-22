package com.azharkova.gistsearcher.services.repository

import com.azharkova.gistsearcher.services.network.retrofit.GistApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.operators.single.SingleError
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GistRepository @Inject constructor(private  val api: GistApi) {

    fun getGistList(page: Int = 0, take: Int = 50) = api.getGistsList(page, take)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun getGistById(id: String) = api.getGistById(id)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun getGistCommits(id: String) = api.getGistCommits(id)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}