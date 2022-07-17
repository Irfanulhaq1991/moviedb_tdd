package com.zenjob.android.browsr.list.data

import com.zenjob.android.browsr.Mapper
import com.zenjob.android.browsr.list.domain.model.Movie

class MoviesRepository(
    private val mapper: Mapper<List<MovieDto>,List<Movie>>,
    private val remoteMoviesListDataSource: RemoteMoviesListDataSource,
) {
    fun fetchMoviesList(): Result<List<Movie>> {
        val result = remoteMoviesListDataSource.fetchMoviesList()
        return result.fold({ Result.success(mapper.map(it))},{ Result.failure(it)})

    }

}
