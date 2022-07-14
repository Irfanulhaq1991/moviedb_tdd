package com.zenjob.android.browsr.list

class FetchingMoviesListUseCase(private val repository: MoviesListRepository) {
    fun fetchMoviesList() {
        repository.fetchMoviesList()
    }

}
