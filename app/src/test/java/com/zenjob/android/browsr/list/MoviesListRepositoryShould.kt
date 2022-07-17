package com.zenjob.android.browsr.list

import com.google.common.truth.Truth.assertThat
import com.zenjob.android.browsr.BaseTest
import com.zenjob.android.browsr.list.MoviesDummyData.provideDomainModelsList
import com.zenjob.android.browsr.list.MoviesDummyData.provideDtoList
import com.zenjob.android.browsr.list.data.MoviesRepository
import com.zenjob.android.browsr.list.data.RemoteDataSource
import com.zenjob.android.browsr.list.domain.MoviesListMapper
import com.zenjob.android.browsr.list.domain.model.Movie
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test
import java.io.IOException

class MoviesListRepositoryShould : BaseTest() {

    private lateinit var mapper: MoviesListMapper

    @MockK
    private lateinit var remoteDataSource: RemoteDataSource

    private lateinit var repo:MoviesRepository

    @Before
    override fun setUp() {
        super.setUp()
        mapper = MoviesListMapper()
        repo = MoviesRepository(mapper,remoteDataSource)
    }

    @Test
    fun returnEmptyDomainMovieList() {
        every { remoteDataSource.fetch() } answers { Result.success(emptyList())}
        assertThat(repo.fetchMoviesList()).isEqualTo(Result.success(emptyList<Movie>()))
    }

    @Test
    fun returnDomainMovieList() {
        every { remoteDataSource.fetch() } answers { Result.success(provideDtoList())}
        assertThat(repo.fetchMoviesList()).isEqualTo(Result.success(provideDomainModelsList()))
    }

    @Test
    fun returnFailure() {

        every { remoteDataSource.fetch() } answers { Result.failure(IOException("No Internet"))}
        assertThat(repo.fetchMoviesList().isFailure).isTrue()
    }


}