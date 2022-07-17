package com.zenjob.android.browsr.list

import com.zenjob.android.browsr.list.data.IMoviesListDataSource
import com.zenjob.android.browsr.list.data.MovieDto
import com.zenjob.android.browsr.list.data.MovieRemoteApi
import com.zenjob.android.browsr.list.data.RemoteMoviesListDataSource
import retrofit2.Response

class RemoteMoviesListDataSourceShould:MoviesListDataSourceContractTest() {

   override fun withNoData(): RemoteMoviesListDataSource {
       val api =  object : MovieRemoteApi {
            override fun fetchMovies(): Response<List<MovieDto>> {
                return Response.success(emptyList())
            }
        }
        return RemoteMoviesListDataSource(api)
    }

    override fun withData(provideDtoList: List<MovieDto>): RemoteMoviesListDataSource {
        val api = object : MovieRemoteApi {
            override fun fetchMovies(): Response<List<MovieDto>> {
                return Response.success(provideDtoList)
            }
        }
        return RemoteMoviesListDataSource(api)
    }

    override fun withError(throwable: Throwable): RemoteMoviesListDataSource {
        val api =  object : MovieRemoteApi {
            override fun fetchMovies(): Response<List<MovieDto>> {
                throw throwable
            }
        }
        return RemoteMoviesListDataSource(api)
    }
}