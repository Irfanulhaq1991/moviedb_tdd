package com.zenjob.android.browsr

import com.google.common.truth.Truth.assertThat
import com.zenjob.android.browsr.list.MoviesListRepository
import org.junit.Before
import org.junit.Test
/*
  - When request is successful and no data is returned then do not map
  - When request is successful and data is returned then Mapping data to Domain models and return it
  - When error is returned it should be propagated up
 */

class MoviesListRepositoryShould : BaseTest(){

    @Before
    override fun setUp() {
        super.setUp()
    }

    @Test
    fun returnEmptyDomainMovieList(){
        val repo = MoviesListRepository()
        assertThat(repo.fetchMoviesList()).isEqualTo(Result.success(emptyList<String>()))
    }

    @Test
    fun returnDomainMovieList(){
        val repo = MoviesListRepository(listOf("1","2","3"))
        assertThat(repo.fetchMoviesList()).isEqualTo(Result.success(listOf("1","2","3")))
    }

    @Test
    fun returnFailure(){
        val repo = MoviesListRepository(null)
        assertThat(repo.fetchMoviesList().isFailure).isTrue()
    }
}