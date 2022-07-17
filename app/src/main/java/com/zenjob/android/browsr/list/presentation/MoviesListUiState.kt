package com.zenjob.android.browsr.list.presentation

import com.zenjob.android.browsr.list.domain.model.Movie

data class MoviesListUiState(
    val moviesList: List<Movie> = emptyList(),
    val isError: Boolean = false,
    val errorMessage: String = "",
    val showLoading: Boolean = false,
)