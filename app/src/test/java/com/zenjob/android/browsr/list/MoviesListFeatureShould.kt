package com.zenjob.android.browsr.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.google.common.truth.Truth.assertThat
import com.zenjob.android.browsr.list.data.MovieDto
import com.zenjob.android.browsr.list.data.MovieRemoteApi
import com.zenjob.android.browsr.list.domain.FetchingMoviesListUseCase
import com.zenjob.android.browsr.list.data.MoviesRepository
import com.zenjob.android.browsr.list.data.MoviesListDataSource
import com.zenjob.android.browsr.list.domain.MoviesListMapper
import com.zenjob.android.browsr.list.presentation.MoviesListViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

class MoviesListFeatureShould {

   private lateinit var  uiController: MovieListingSpyUiController
   private val moviesRemoteApi = object : MovieRemoteApi {
       override fun fetchMovies(): Response<List<MovieDto>> {
           return Response.success(MoviesDummyData.MoviesDtosList())
       }
   }


   @get:Rule
   val rule = InstantTaskExecutorRule()

   @Before
   fun setup(){
       val remoteDataSource = MoviesListDataSource(moviesRemoteApi)
       val mapper = MoviesListMapper()
       val fetchMoviesListFeatureRepository = MoviesRepository(mapper,remoteDataSource)
       val fetchingMoviesListUseCase = FetchingMoviesListUseCase(fetchMoviesListFeatureRepository)
       val moviesListViewModel = MoviesListViewModel(fetchingMoviesListUseCase)
       uiController = MovieListingSpyUiController().apply { viewModel = moviesListViewModel }
       uiController.onCreate()
   }


    @Test
    fun fetchMoviesList(){
        val actual = listOf<String>("Loading","success","HideLoading")
        uiController.fetchMoviesList()
        val result = uiController.uiStates
        assertThat(result).isEqualTo(actual)
    }
}

class MovieListingSpyUiController:LifecycleOwner {

    lateinit var viewModel: MoviesListViewModel
    val uiStates = mutableListOf<String>()

    private val registry: LifecycleRegistry by lazy { LifecycleRegistry(this) }
    override fun getLifecycle() = registry

    fun onCreate(){
        registry.currentState = Lifecycle.State.STARTED
        viewModel.fetchMoviesListLiveData.observe(this,{
            uiStates.add(it)
        })
    }


    fun fetchMoviesList() {
        viewModel.fetchMoviesList()
    }

}