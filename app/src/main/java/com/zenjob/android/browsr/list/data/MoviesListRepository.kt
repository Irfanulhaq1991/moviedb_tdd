package com.zenjob.android.browsr.list.data

import com.zenjob.android.browsr.list.domain.MoviesListMapper
import com.zenjob.android.browsr.list.domain.model.MovieDomainModel

class MoviesListRepository(private val mapper: MoviesListMapper, private val list: List<MovieDto>? = emptyList()) {
    fun fetchMoviesList(): Result<List<MovieDomainModel>> {
        return when {
            list == null -> Result.failure(Throwable())
            list.isEmpty() -> Result.success(emptyList())
            else -> Result.success(mapper.map(list))
        }
    }

}
