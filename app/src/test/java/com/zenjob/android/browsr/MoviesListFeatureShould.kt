package com.zenjob.android.browsr

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MoviesListingFeatureShould {

   private lateinit var  uiController: MovieListingSpyUiController

   @get:Rule
   val rule = InstantTaskExecutorRule()

   @Before
   fun setup(){
       val moviesListViewModel = MoviesListViewModel()
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