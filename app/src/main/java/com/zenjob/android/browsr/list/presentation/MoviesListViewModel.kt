package com.zenjob.android.browsr.list.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zenjob.android.browsr.list.domain.FetchingMoviesListUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MoviesListViewModel(private val fetchingMoviesListUseCase: FetchingMoviesListUseCase) :
    ViewModel() {

    private val _uiState = MutableLiveData(MoviesListUiState())
    val uiState: LiveData<MoviesListUiState> = _uiState
    private var fetchJob: Job? = null

    fun fetchMoviesList(isForcedRefresh: Boolean = false) {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            _uiState.value = uiState.value!!.copy(showLoading = !isForcedRefresh)
            fetchingMoviesListUseCase.fetchMoviesList().let { result ->
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
        }
    }

    fun userMessageShown() {
        uiState.value!!.copy(errorMessage = "")
    }

}





