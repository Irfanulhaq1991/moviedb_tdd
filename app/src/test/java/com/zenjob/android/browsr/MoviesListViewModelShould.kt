package com.zenjob.android.browsr

import com.zenjob.android.browsr.list.FetchingMoviesListUseCase
import com.zenjob.android.browsr.list.MoviesListViewModel
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import junit.runner.BaseTestRunner
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MoviesListViewModelShould:BaseTest() {

    @MockK
    lateinit var fetchingMoviesListUseCase: FetchingMoviesListUseCase

    private lateinit var viewModel: MoviesListViewModel

    @Before
    override fun setup(){
        super.setup()
        viewModel = MoviesListViewModel(fetchingMoviesListUseCase)
    }
    @Test
    fun fetchMoviesList(){
        viewModel.fetchMoviesList()
        verify { fetchingMoviesListUseCase.fetchMoviesList() }
    }
}