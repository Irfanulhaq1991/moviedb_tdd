package com.zenjob.android.browsr.list.domain

import com.zenjob.android.browsr.list.data.MoviesListRepository

class FetchingMoviesListUseCase(private val repository: MoviesListRepository) {
    fun fetchMoviesList() {
        repository.fetchMoviesList()
    }

}
