package com.zenjob.android.browsr.list.data

import java.io.IOException

class RemoteMoviesListDataSource(private val movieDtoList: List<MovieDto>? = emptyList()) {
    fun fetchMoviesList():Result<List<MovieDto>> {
       try {
        if(movieDtoList == null){
            throw IOException()
        }else{
            return Result.success(movieDtoList!!)
        }
       }catch (e:IOException){
           return Result.failure(Throwable("No Internet"))
       }

    }

}
