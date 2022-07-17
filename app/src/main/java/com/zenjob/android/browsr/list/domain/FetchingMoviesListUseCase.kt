package com.zenjob.android.browsr.list.domain

import com.zenjob.android.browsr.list.data.MoviesRepository

class FetchingMoviesListUseCase(private val repository: MoviesRepository) {
    fun fetchMoviesList() {
        repository.fetchMoviesList()
    }

}
