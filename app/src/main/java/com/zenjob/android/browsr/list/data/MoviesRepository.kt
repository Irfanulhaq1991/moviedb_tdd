package com.zenjob.android.browsr.list.data

import com.zenjob.android.browsr.Mapper
import com.zenjob.android.browsr.list.domain.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesRepository(
    private val mapper: Mapper<List<MovieDto>,List<Movie>>,
    private val remoteMoviesListDataSource: IMoviesListDataSource,
) {
    suspend fun fetchMoviesList(): Result<List<Movie>> {
        return withContext(Dispatchers.IO) {
            val result = remoteMoviesListDataSource.fetchMoviesList()
            result.fold({ Result.success(mapper.map(it)) }, { Result.failure(it) })
        }
    }

}
