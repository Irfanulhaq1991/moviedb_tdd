package com.zenjob.android.browsr.list.data

class MoviesListRepository(val list: List<String>? = emptyList()) {
    fun fetchMoviesList(): Result<List<String>> {
        return when {
            list == null -> Result.failure(Throwable())
            list.isEmpty() -> Result.success(emptyList())
            else -> Result.success(list)
        }
    }

}
