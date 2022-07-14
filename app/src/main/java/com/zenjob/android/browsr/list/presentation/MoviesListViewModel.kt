package com.zenjob.android.browsr.list

import androidx.lifecycle.MutableLiveData

class MoviesListViewModel(private val fetchingMoviesListUseCase: FetchingMoviesListUseCase) {

    val fetchMoviesListLiveData = MutableLiveData<String>()

    fun fetchMoviesList() {
        fetchingMoviesListUseCase.fetchMoviesList()
    }

}
