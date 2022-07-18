package com.zenjob.android.browsr.list.presentation

import com.zenjob.android.browsr.list.domain.model.Movie

data class MoviesListUiState(
    val moviesList: List<Movie> = emptyList(),
    val errorMessage: String? = null,
    val showLoading: Boolean = false,
)