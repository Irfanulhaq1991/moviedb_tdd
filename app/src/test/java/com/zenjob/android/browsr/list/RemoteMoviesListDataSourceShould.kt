package com.zenjob.android.browsr.list

import com.google.common.truth.Truth.assertThat
import com.zenjob.android.browsr.BaseTest
import com.zenjob.android.browsr.list.MoviesDummyData.MoviesDtosList
import com.zenjob.android.browsr.list.data.MovieDto
import com.zenjob.android.browsr.list.data.MovieRemoteApi
import com.zenjob.android.browsr.list.data.RemoteMoviesListDataSource
import org.junit.Test
import retrofit2.Response
import java.io.IOException

class RemoteMoviesListDataSourceShould : BaseTest() {

    @Test
    fun returnEmptyMoviesDtoList() {
        val dataSource = withNoData()
        val result = dataSource.fetchMoviesList()
        assertThat(result)
            .isEqualTo(Result.success(emptyList<MovieDto>()))

    }

    @Test
    fun returnMoviesDtoList() {
        val dataSource = withData(MoviesDtosList())
        val result = dataSource.fetchMoviesList()
        assertThat(result)
            .isEqualTo(Result.success(MoviesDtosList()))
    }


    @Test
    fun returnErrorIfExceptionIsThrown() {
        val dataSource = withError(IOException("No Internet"))
        val result = dataSource.fetchMoviesList()
        assertThat(isFailureWithMessage(result, "No Internet")).isTrue()

    }




    private fun withNoData(): RemoteMoviesListDataSource {
       val api =  object : MovieRemoteApi {
            override fun fetchMovies(): Response<List<MovieDto>> {
                return Response.success(emptyList())
            }
        }
        return RemoteMoviesListDataSource(api)
    }

    private fun withData(provideDtoList: List<MovieDto>): RemoteMoviesListDataSource {
        val api = object : MovieRemoteApi {
            override fun fetchMovies(): Response<List<MovieDto>> {
                return Response.success(provideDtoList)
            }
        }
        return RemoteMoviesListDataSource(api)
    }

    private fun withError(throwable: Throwable): RemoteMoviesListDataSource {
        val api =  object : MovieRemoteApi {
            override fun fetchMovies(): Response<List<MovieDto>> {
                throw throwable
            }
        }
        return RemoteMoviesListDataSource(api)
    }

}