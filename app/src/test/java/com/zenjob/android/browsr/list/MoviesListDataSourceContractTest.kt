package com.zenjob.android.browsr.list

import com.google.common.truth.Truth.assertThat
import com.zenjob.android.browsr.BaseTest
import com.zenjob.android.browsr.list.MoviesDummyData.MoviesDtosList
import com.zenjob.android.browsr.list.data.IMoviesListDataSource
import com.zenjob.android.browsr.list.data.MovieDto
import com.zenjob.android.browsr.list.data.MovieRemoteApi
import com.zenjob.android.browsr.list.data.RemoteMoviesListDataSource
import kotlinx.coroutines.test.runTest
import org.junit.Test
import retrofit2.Response
import java.io.IOException


abstract class MoviesListDataSourceContractTest : BaseTest() {

    @Test
    fun returnEmptyMoviesDtoList() = runTest{
        val dataSource = withNoData()
        val result = dataSource.fetchMoviesList()
        assertThat(result)
            .isEqualTo(Result.success(emptyList<MovieDto>()))

    }

    @Test
    fun returnMoviesDtoList() = runTest {
        val dataSource = withData(MoviesDtosList())
        val result = dataSource.fetchMoviesList()
        assertThat(result)
            .isEqualTo(Result.success(MoviesDtosList()))
    }


    @Test
    fun returnErrorIfExceptionIsThrown()= runTest {
        val dataSource = withError(IOException())
        val result = dataSource.fetchMoviesList()
        assertThat(isFailureWithMessage(result, "No Internet")).isTrue()

    }

    abstract fun withNoData(): IMoviesListDataSource

    abstract fun withData(provideDtoList: List<MovieDto>): IMoviesListDataSource

    abstract fun withError(throwable: Throwable): IMoviesListDataSource



}