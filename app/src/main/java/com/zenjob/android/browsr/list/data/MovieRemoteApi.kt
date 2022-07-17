package com.zenjob.android.browsr.list.data

import retrofit2.Response
import retrofit2.http.GET

interface MovieRemoteApi {
    @GET("#")
    fun fetchMovies():Response<List<MovieDto>>

}
