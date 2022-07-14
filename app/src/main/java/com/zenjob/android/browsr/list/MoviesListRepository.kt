package com.zenjob.android.browsr.list

class MoviesListRepository(val list: List<String>? = emptyList()) {
    fun fetchMoviesList(): Result<List<String>> {
        if (list == null)
            return Result.failure(Throwable())
        else if (list.isEmpty())
            return Result.success(emptyList<String>())
        else
            return Result.success(list)
    }

}
