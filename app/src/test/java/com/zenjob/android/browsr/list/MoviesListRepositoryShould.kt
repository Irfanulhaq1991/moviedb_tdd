package com.zenjob.android.browsr.list

import com.google.common.truth.Truth.assertThat
import com.zenjob.android.browsr.BaseTest
import com.zenjob.android.browsr.list.MoviesDummyData.provideDomainModelsList
import com.zenjob.android.browsr.list.MoviesDummyData.provideDtoList
import com.zenjob.android.browsr.list.data.MoviesListRepository
import com.zenjob.android.browsr.list.domain.MoviesListMapper
import com.zenjob.android.browsr.list.domain.model.Movie
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test
/*
  - When request is successful and no data is returned then do not map
  - When request is successful and data is returned then Mapping data to Domain models and return it
  - When error is returned it should be propagated up
 */

class MoviesListRepositoryShould : BaseTest(){
    @MockK
    private lateinit var mapper: MoviesListMapper

    @Before
    override fun setUp() {
        super.setUp()
    }

    @Test
    fun returnEmptyDomainMovieList(){
        val repo = MoviesListRepository(mapper)
        every { mapper.map(any()) } answers { provideDomainModelsList()}
        assertThat(repo.fetchMoviesList()).isEqualTo(Result.success(emptyList<Movie>()))
    }

    @Test
    fun returnDomainMovieList(){

        val repo = MoviesListRepository(mapper,provideDtoList())
        every { mapper.map(any()) } answers { provideDomainModelsList()}
        assertThat(repo.fetchMoviesList()).isEqualTo(Result.success(provideDomainModelsList()))
    }

    @Test
    fun returnFailure(){

        val repo = MoviesListRepository(mapper, null)
        assertThat(repo.fetchMoviesList().isFailure).isTrue()
    }


}