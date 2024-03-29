package com.zenjob.android.browsr.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.google.common.truth.Truth.assertThat
import com.zenjob.android.browsr.CoroutineTestRule
import com.zenjob.android.browsr.MoviesDummyData
import com.zenjob.android.browsr.list.data.remote.MoviePageDto
import com.zenjob.android.browsr.list.data.remote.MovieRemoteApi
import com.zenjob.android.browsr.list.data.remote.MoviesRepository
import com.zenjob.android.browsr.list.data.remote.RemoteMoviesListDataSource
import com.zenjob.android.browsr.list.domain.FetchingMoviesListUseCase
import com.zenjob.android.browsr.list.domain.MoviesListMapper
import com.zenjob.android.browsr.list.presentation.MoviesListUiState
import com.zenjob.android.browsr.list.presentation.MoviesListViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class MoviesListFeatureShould {

    @get:Rule
    val liveDataRule = InstantTaskExecutorRule()
    @get:Rule
    val coroutineRul = CoroutineTestRule()


    private lateinit var  uiController: MovieListingSpyUiController
    private val moviesRemoteApi = object : MovieRemoteApi {
        override suspend fun fetchMovies(): Response<MoviePageDto> {
            return Response.success(MoviesDummyData.provideMoviesDtosList())
        }
    }

    @Before
   fun setup(){
       val remoteDataSource = RemoteMoviesListDataSource(moviesRemoteApi)
       val mapper = MoviesListMapper()
       val fetchMoviesListFeatureRepository = MoviesRepository(mapper,remoteDataSource)
       val fetchingMoviesListUseCase = FetchingMoviesListUseCase(fetchMoviesListFeatureRepository)
       val moviesListViewModel = MoviesListViewModel(fetchingMoviesListUseCase)
       uiController = MovieListingSpyUiController().apply { viewModel = moviesListViewModel }
       uiController.onCreate()
   }


    @Test
    fun fetchMoviesList(){
        val actual = listOf(MoviesListUiState(),MoviesListUiState(showLoading = true),MoviesListUiState(
            MoviesDummyData.provideDomainModelsList().movies))
        uiController.fetchMoviesList()
        val result = uiController.uiStates
        assertThat(result).isEqualTo(actual)
    }
}

class MovieListingSpyUiController:LifecycleOwner {

    lateinit var viewModel: MoviesListViewModel
    val uiStates = mutableListOf<MoviesListUiState>()
    private val countDownLatch: CountDownLatch = CountDownLatch(1)


    private val registry: LifecycleRegistry by lazy { LifecycleRegistry(this) }
    override fun getLifecycle() = registry

    fun onCreate(){
        registry.currentState = Lifecycle.State.STARTED
        viewModel.uiState.observe(this,{
            uiStates.add(it)
            if(uiStates.size == 3) {
                countDownLatch.countDown()
            }
        })
    }


    fun fetchMoviesList() {
        viewModel.fetchMoviesList()
        countDownLatch.await(100, TimeUnit.MILLISECONDS)

    }

}