package com.zenjob.android.browsr.list

import com.google.common.truth.Truth.assertThat
import com.zenjob.android.browsr.MoviesDummyData
import com.zenjob.android.browsr.list.domain.MoviesListMapper
import kotlinx.coroutines.test.runTest
import org.junit.Test

class MoviesListMapperShould {


    @Test
    fun returnListOfSizeInput() = runTest{
        val input = MoviesDummyData.provideMoviesDtosList()
        val mapper = MoviesListMapper()
        val output = mapper.map(input)
        assertThat(output.movies.size).isEqualTo(input.results.size)
    }

    @Test
    fun returnListOfDomainModels() = runTest{
        val input = MoviesDummyData.provideMoviesDtosList()
        val expected = MoviesDummyData.provideDomainModelsList()
        val mapper = MoviesListMapper()
        val output = mapper.map(input)
        assertThat(output).isEqualTo(expected)
    }
}