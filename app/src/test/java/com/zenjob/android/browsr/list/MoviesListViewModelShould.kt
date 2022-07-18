package com.zenjob.android.browsr.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.zenjob.android.browsr.BaseTest
import com.zenjob.android.browsr.CoroutineTestRule
import com.zenjob.android.browsr.list.domain.FetchingMoviesListUseCase
import com.zenjob.android.browsr.list.domain.model.Movie
import com.zenjob.android.browsr.list.presentation.MoviesListViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MoviesListViewModelShould: BaseTest() {
    @get:Rule
    val liveDataRule = InstantTaskExecutorRule()
    @get:Rule
    val coroutineRul = CoroutineTestRule()

    @RelaxedMockK
    lateinit var fetchingMoviesListUseCase: FetchingMoviesListUseCase

    private lateinit var viewModel: MoviesListViewModel

    @Before
    override fun setUp(){
        super.setUp()
        viewModel = MoviesListViewModel(fetchingMoviesListUseCase)
    }
    @Test
    fun fetchMoviesList()= runTest{
        viewModel.fetchMoviesList()
        coVerify { fetchingMoviesListUseCase.fetchMoviesList() }
    }
}