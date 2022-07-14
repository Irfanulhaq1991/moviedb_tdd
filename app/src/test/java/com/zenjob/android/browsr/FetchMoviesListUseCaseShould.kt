package com.zenjob.android.browsr

import com.zenjob.android.browsr.list.FetchingMoviesListUseCase
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class FetchMoviesListUseCaseShould : BaseTest() {
    @MockK
    private lateinit var moviesListRepo: MoviesListRepository

    private lateinit var fetchMoviesListUseCase:FetchingMoviesListUseCase

    @Before
    override fun setUp() {
        super.setUp()
        fetchMoviesListUseCase = FetchingMoviesListUseCase(moviesListRepo)
    }

    @Test
    fun fetchMoviesList() {
        fetchMoviesListUseCase.fetchMoviesList()
        verify { moviesListRepo.fetchMoviesList() }
    }
}