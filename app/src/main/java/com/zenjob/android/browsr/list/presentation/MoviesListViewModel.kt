package com.zenjob.android.browsr.list.presentation

import androidx.lifecycle.MutableLiveData
import com.zenjob.android.browsr.list.domain.FetchingMoviesListUseCase

class MoviesListViewModel(private val fetchingMoviesListUseCase: FetchingMoviesListUseCase) {

    val fetchMoviesListLiveData = MutableLiveData<String>()

    fun fetchMoviesList() {
        fetchingMoviesListUseCase.fetchMoviesList()
    }

}
