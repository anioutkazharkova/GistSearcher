package com.azharkova.gistsearcher.services.network.retrofit

import com.azharkova.gistsearcher.data.GistCommitInfo
import com.azharkova.gistsearcher.data.GistInfo
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GistApi {

    @GET("gists/public")
    fun getGistsList(@Query("page") page: Int = 0, @Query("per_page")take: Int = 20):Single<List<GistInfo>>

    @GET("gists/{id}")
    fun getGistById(@Path("id")id: String):Single<GistInfo>

    @GET("gists/{id}/commits")
    fun getGistCommits(@Path("id")id: String):Single<List<GistCommitInfo>>

}