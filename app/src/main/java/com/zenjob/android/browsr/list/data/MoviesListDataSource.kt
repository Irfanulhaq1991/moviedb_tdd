package com.zenjob.android.browsr.list.data

import java.io.IOException

class RemoteMoviesListDataSource(private val moviesRemoteApi:MovieRemoteApi) :
    IMoviesListDataSource {
   override suspend fun fetchMoviesList():Result<MoviePageDto> {
        return try {
            val  response = moviesRemoteApi.fetchMovies()
            if(response.isSuccessful){
                Result.success(response.body()!!)
            }else{
                Result.failure(Throwable(""))
            }
        }catch (e:IOException){
            Result.failure(Throwable("No Internet"))
        }

    }

}
