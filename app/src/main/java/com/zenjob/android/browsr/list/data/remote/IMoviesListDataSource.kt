package com.zenjob.android.browsr.list.data.remote

interface IMoviesListDataSource{
    suspend fun fetchMoviesList():Result<MoviePageDto>
}