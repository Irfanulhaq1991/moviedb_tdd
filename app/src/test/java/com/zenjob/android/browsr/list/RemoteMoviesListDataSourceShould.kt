package com.zenjob.android.browsr.list

import com.zenjob.android.browsr.list.data.remote.IMoviesListDataSource
import com.zenjob.android.browsr.list.data.remote.MoviePageDto
import com.zenjob.android.browsr.list.data.remote.MovieRemoteApi
import com.zenjob.android.browsr.list.data.remote.RemoteMoviesListDataSource
import retrofit2.Response

class RemoteMoviesListDataSourceShould:MoviesListDataSourceContractTest() {

   override fun withNoData(): RemoteMoviesListDataSource {
       val api =  object : MovieRemoteApi {
            override suspend fun fetchMovies(): Response<MoviePageDto> {
                return Response.success(MoviePageDto(0,0,0, emptyList()))
            }
        }
        return RemoteMoviesListDataSource(api)
    }


    override fun withData(movieRawData: MoviePageDto): IMoviesListDataSource {
        val api = object : MovieRemoteApi {
            override suspend fun fetchMovies(): Response<MoviePageDto> {
                return Response.success(movieRawData)
            }
        }
        return RemoteMoviesListDataSource(api)
    }

    override fun withError(throwable: Throwable): RemoteMoviesListDataSource {
        val api =  object : MovieRemoteApi {
            override suspend fun fetchMovies(): Response<MoviePageDto> {
                throw throwable
            }
        }
        return RemoteMoviesListDataSource(api)
    }
}