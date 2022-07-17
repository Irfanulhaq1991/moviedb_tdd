package com.zenjob.android.browsr.list.data

interface IMoviesListDataSource{
    suspend fun fetchMoviesList():Result<List<MovieDto>>
}