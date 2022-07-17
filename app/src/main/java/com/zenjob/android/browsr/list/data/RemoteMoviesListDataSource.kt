package com.zenjob.android.browsr.list.data

class RemoteMoviesListDataSource {
    fun fetchMoviesList():Result<List<MovieDto>> {
        return Result.success(emptyList())
    }

}
