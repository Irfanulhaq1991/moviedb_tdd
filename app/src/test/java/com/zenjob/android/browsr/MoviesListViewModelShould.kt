package com.zenjob.android.browsr

import com.zenjob.android.browsr.list.FetchingMoviesListUseCase
import com.zenjob.android.browsr.list.MoviesListViewModel
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class MoviesListViewModelShould:BaseTest() {

    @MockK
    lateinit var fetchingMoviesListUseCase: FetchingMoviesListUseCase

    private lateinit var viewModel: MoviesListViewModel

    @Before
    override fun setUp(){
        super.setUp()
        viewModel = MoviesListViewModel(fetchingMoviesListUseCase)
    }
    @Test
    fun fetchMoviesList(){
        viewModel.fetchMoviesList()
        verify { fetchingMoviesListUseCase.fetchMoviesList() }
    }
}