package com.zenjob.android.browsr.list.data

class RemoteMoviesListDataSource(val movieDtoList: List<MovieDto> = emptyList()) {
    fun fetchMoviesList():Result<List<MovieDto>> {
        return Result.success(movieDtoList)
    }

}
