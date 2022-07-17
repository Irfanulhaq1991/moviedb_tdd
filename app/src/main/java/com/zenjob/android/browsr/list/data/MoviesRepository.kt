package com.zenjob.android.browsr.list.data

import com.zenjob.android.browsr.Mapper
import com.zenjob.android.browsr.list.domain.model.Movie

class MoviesListRepository(
    private val mapper: Mapper<List<MovieDto>,List<Movie>>,
    private val list: List<MovieDto>? = emptyList()
) {
    fun fetchMoviesList(): Result<List<Movie>> {
        return when {
            list == null -> Result.failure(Throwable())
            list.isEmpty() -> Result.success(emptyList())
            else -> Result.success(mapper.map(list))
        }
    }

}
