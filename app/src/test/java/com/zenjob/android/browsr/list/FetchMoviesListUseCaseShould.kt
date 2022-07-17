package com.zenjob.android.browsr.list

import com.zenjob.android.browsr.BaseTest
import com.zenjob.android.browsr.list.domain.FetchingMoviesListUseCase
import com.zenjob.android.browsr.list.data.MoviesRepository
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class FetchMoviesListUseCaseShould : BaseTest() {
    @RelaxedMockK
    private lateinit var moviesRepo: MoviesRepository

    private lateinit var fetchMoviesListUseCase: FetchingMoviesListUseCase

    @Before
    override fun setUp() {
        super.setUp()
        fetchMoviesListUseCase = FetchingMoviesListUseCase(moviesRepo)
    }

    @Test
    fun fetchMoviesList() {
        fetchMoviesListUseCase.fetchMoviesList()
        verify { moviesRepo.fetchMoviesList() }
    }
}