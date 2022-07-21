package com.zenjob.android.browsr.list.data.remote

import com.zenjob.android.browsr.common.Mapper
import com.zenjob.android.browsr.list.domain.model.Movies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesRepository(
    private val mapper: Mapper<MoviePageDto, Movies>,
    private val remoteMoviesListDataSource: IMoviesListDataSource,
) {
    suspend fun fetchMoviesList(): Result<Movies> {
        return withContext(Dispatchers.IO) {
            val result = remoteMoviesListDataSource.fetchMoviesList()
            result.fold({ Result.success(mapper.map(it)) }, { Result.failure(it) })
        }
    }

}
