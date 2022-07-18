package com.zenjob.android.browsr.list.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zenjob.android.browsr.list.domain.FetchingMoviesListUseCase
import com.zenjob.android.browsr.list.domain.model.Movie
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MoviesListViewModel(private val fetchingMoviesListUseCase: FetchingMoviesListUseCase) :
    ViewModel() {

    private val _uiState = MutableLiveData(MoviesListUiState())
    val uiState: LiveData<MoviesListUiState> = _uiState
    private var fetchJob: Job? = null

    fun fetchMoviesList() {
        fetchJob?.cancel()
        proceed()

    }

    private fun proceed(){
        fetchJob = viewModelScope.launch {

            _uiState.value = uiState.value!!.copy(showLoading = true)
            fetchingMoviesListUseCase.fetchMoviesList().run {
                reduceState(this)
            }
        }
    }

    private fun reduceState(result: Result<List<Movie>>) {
        result.fold({
            _uiState.value = uiState.value!!
                .copy(
                    moviesList = it,
                    showLoading = false
                )

        }, {
            _uiState.value = uiState.value!!
                .copy(
                    errorMessage = it.message!!,
                    showLoading = false
                )

        })
    }

    fun userMessageShown() {
        uiState.value!!.copy(errorMessage = "")
    }

}





