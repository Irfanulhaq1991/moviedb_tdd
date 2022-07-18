package com.zenjob.android.browsr.list

import com.google.common.truth.Truth.assertThat
import com.zenjob.android.browsr.BaseTest
import com.zenjob.android.browsr.list.MoviesDummyData.provideMoviesDtosList
import com.zenjob.android.browsr.list.data.IMoviesListDataSource
import com.zenjob.android.browsr.list.data.MovieDto
import com.zenjob.android.browsr.list.data.MoviePageDto
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.io.IOException


abstract class MoviesListDataSourceContractTest : BaseTest() {

    @Test
    fun returnEmptyMoviesDtoList() = runTest{
        val dataSource = withNoData()
        val result = dataSource.fetchMoviesList()
        assertThat(result)
            .isEqualTo(Result.success(MoviePageDto(0,0,0,emptyList())))

    }

    @Test
    fun returnMoviesDtoList() = runTest {
        val dataSource = withData(provideMoviesDtosList())
        val result = dataSource.fetchMoviesList()
        assertThat(result)
            .isEqualTo(Result.success(provideMoviesDtosList()))
    }


    @Test
    fun returnErrorIfExceptionIsThrown()= runTest {
        val dataSource = withError(IOException())
        val result = dataSource.fetchMoviesList()
        assertThat(isFailureWithMessage(result, "No Internet")).isTrue()

    }

    abstract fun withNoData(): IMoviesListDataSource

    abstract fun withData(provideDtoList:MoviePageDto): IMoviesListDataSource

    abstract fun withError(throwable: Throwable): IMoviesListDataSource



}