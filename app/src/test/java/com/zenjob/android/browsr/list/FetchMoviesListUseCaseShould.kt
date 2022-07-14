package com.zenjob.android.browsr.list

import com.zenjob.android.browsr.BaseTest
import com.zenjob.android.browsr.list.domain.FetchingMoviesListUseCase
import com.zenjob.android.browsr.list.data.MoviesListRepository
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class FetchMoviesListUseCaseShould : BaseTest() {
    @RelaxedMockK
    private lateinit var moviesListRepo: MoviesListRepository

    private lateinit var fetchMoviesListUseCase: FetchingMoviesListUseCase

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