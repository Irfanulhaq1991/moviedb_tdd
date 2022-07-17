package com.zenjob.android.browsr.list

import com.google.common.truth.Truth.assertThat
import com.zenjob.android.browsr.list.data.MovieDto
import com.zenjob.android.browsr.list.data.RemoteMoviesListDataSource
import org.junit.Test

class RemoteMoviesListDataSourceShould{

    @Test
    fun returnEmptyMoviesDtoList(){

        assertThat(
            RemoteMoviesListDataSource()
            .fetchMoviesList())
            .isEqualTo(Result.success(emptyList<MovieDto>()))

    }

    @Test
    fun returnMoviesDtoList(){
        assertThat(
            RemoteMoviesListDataSource(MoviesDummyData.provideDtoList())
            .fetchMoviesList())
            .isEqualTo(Result.success(MoviesDummyData.provideDtoList()))
    }


}