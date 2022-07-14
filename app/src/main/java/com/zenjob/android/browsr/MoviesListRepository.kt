package com.zenjob.android.browsr

class MoviesListRepository() {
    fun fetchMoviesList(): Result<List<String>> {
       return Result.success(emptyList<String>())
    }

}
