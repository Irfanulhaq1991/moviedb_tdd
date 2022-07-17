package com.zenjob.android.browsr.list.data

interface IMoviesListDataSource{
    fun fetchMoviesList():Result<List<MovieDto>>
}