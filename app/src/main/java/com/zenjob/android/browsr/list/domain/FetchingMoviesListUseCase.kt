package com.zenjob.android.browsr.list.domain

import com.zenjob.android.browsr.list.data.MoviesRepository
import com.zenjob.android.browsr.list.domain.model.Movie

class FetchingMoviesListUseCase(private val repository: MoviesRepository) {
    suspend fun fetchMoviesList(): Result<List<Movie>> {
        return repository.fetchMoviesList().fold({
            val pageNo = it.pageId
                 /* save page number */
            Result.success(it.movies)
        }, {
            Result.failure(it)
        })
    }

}
