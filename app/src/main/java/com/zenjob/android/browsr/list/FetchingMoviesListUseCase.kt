package com.zenjob.android.browsr.list

import com.zenjob.android.browsr.MoviesListRepository

class FetchingMoviesListUseCase(private val repository: MoviesListRepository) {
    fun fetchMoviesList() {
        repository.fetchMoviesList()
    }

}
