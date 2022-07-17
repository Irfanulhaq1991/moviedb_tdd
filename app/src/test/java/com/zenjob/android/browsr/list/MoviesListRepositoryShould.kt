package com.zenjob.android.browsr.list

import com.google.common.truth.Truth.assertThat
import com.zenjob.android.browsr.BaseTest
import com.zenjob.android.browsr.list.MoviesDummyData.provideDomainModelsList
import com.zenjob.android.browsr.list.MoviesDummyData.provideDtoList
import com.zenjob.android.browsr.list.data.MoviesRepository
import com.zenjob.android.browsr.list.data.RemoteMoviesListDataSource
import com.zenjob.android.browsr.list.domain.MoviesListMapper
import com.zenjob.android.browsr.list.domain.model.Movie
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test
import java.io.IOException

class MoviesListRepositoryShould : BaseTest() {

    @MockK
    private lateinit var remoteMoviesListDataSource: RemoteMoviesListDataSource

    private lateinit var mapper: MoviesListMapper
    private lateinit var repo:MoviesRepository

    @Before
    override fun setUp() {
        super.setUp()

        mapper = MoviesListMapper()
        repo = MoviesRepository(mapper,remoteMoviesListDataSource)
    }

    @Test
    fun returnEmptyDomainMovieList() {
        every { remoteMoviesListDataSource.fetchMoviesList() } answers { Result.success(emptyList())}
        assertThat(repo.fetchMoviesList()).isEqualTo(Result.success(emptyList<Movie>()))
    }

    @Test
    fun returnDomainMovieList() {
        every { remoteMoviesListDataSource.fetchMoviesList() } answers { Result.success(provideDtoList())}
        assertThat(repo.fetchMoviesList()).isEqualTo(Result.success(provideDomainModelsList()))
    }

    @Test
    fun returnFailure() {

        every { remoteMoviesListDataSource.fetchMoviesList() } answers { Result.failure(IOException("No Internet"))}
        assertThat(repo.fetchMoviesList().isFailure).isTrue()
    }


}