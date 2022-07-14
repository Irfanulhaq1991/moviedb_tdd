package com.zenjob.android.browsr.data

import com.zenjob.android.browsr.list.data.MovieDto
import com.zenjob.android.browsr.list.data.MoviePageDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBApi {
    @GET("movie/popular")
    fun getPopularTvShows(
        @Query("language") query: String? = null,
        @Query("page") page: Int? = null
    ): Single<MoviePageDto>


    @GET("movie/{movie_id}")
    fun getDetails(
        @Path("movie_id") movieId: Long,
        @Query("language") query: String? = null
    ): Single<MovieDto>
}
