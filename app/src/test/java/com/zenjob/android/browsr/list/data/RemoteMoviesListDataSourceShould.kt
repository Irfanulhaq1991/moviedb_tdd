package com.zenjob.android.browsr.list.data

import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase
import org.junit.Test

class RemoteMoviesListDataSourceShould{

    @Test
    fun fetchMoviesList(){

        assertThat(RemoteMoviesListDataSource()
            .fetchMoviesList())
            .isEqualTo(Result.success(emptyList<MovieDto>()))

    }

}