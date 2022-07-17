package com.zenjob.android.browsr.list.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zenjob.android.browsr.list.domain.FetchingMoviesListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class MoviesListViewModel(private val fetchingMoviesListUseCase: FetchingMoviesListUseCase) {

    private val _uiState = MutableLiveData(MoviesListUiState())
    val uiState: LiveData<MoviesListUiState> = _uiState

    fun fetchMoviesList() {
        _uiState.value = uiState.value!!.copy(showLoading = true)
        fetchingMoviesListUseCase.fetchMoviesList().let { result ->
            result.fold({
                _uiState.value = uiState.value!!.copy(moviesList = it,showLoading = false)

            }, {
                _uiState.value =
                    uiState.value!!.copy(isError = result.isFailure, errorMessage = it.message!!,showLoading = false)

            })
        }
    }

    fun userMessageShown() {
        uiState.value!!.copy(isError = false, errorMessage = "")
    }

}





