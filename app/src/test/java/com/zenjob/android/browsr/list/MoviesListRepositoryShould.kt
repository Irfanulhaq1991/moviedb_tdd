package com.zenjob.android.browsr.list

import com.google.common.truth.Truth.assertThat
import com.zenjob.android.browsr.BaseTest
import com.zenjob.android.browsr.CoroutineTestRule
import com.zenjob.android.browsr.MoviesDummyData.provideMoviesDtosList
import com.zenjob.android.browsr.MoviesDummyData.provideDomainModelsList
import com.zenjob.android.browsr.list.data.remote.MoviePageDto
import com.zenjob.android.browsr.list.data.remote.MoviesRepository
import com.zenjob.android.browsr.list.data.remote.RemoteMoviesListDataSource
import com.zenjob.android.browsr.list.domain.MoviesListMapper
import com.zenjob.android.browsr.list.domain.model.Movies
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class MoviesListRepositoryShould : BaseTest() {

    @get:Rule
    val rule = CoroutineTestRule()

    @MockK
    private lateinit var remoteMoviesListDataSource: RemoteMoviesListDataSource

    private lateinit var mapper: MoviesListMapper
    private lateinit var repo: MoviesRepository

    @Before
    override fun setUp() {
        super.setUp()

        mapper = MoviesListMapper()
        repo = MoviesRepository(mapper, remoteMoviesListDataSource)
    }

    @Test
    fun returnEmptyDomainMovieList() = runTest {
        coEvery { remoteMoviesListDataSource.fetchMoviesList() } answers { Result.success(
            MoviePageDto(0, 0, 0, emptyList())
        ) }
        assertThat(repo.fetchMoviesList())
            .isEqualTo(Result.success(Movies(0,emptyList())))
    }

    @Test
    fun returnDomainMovieList() = runTest {
        coEvery { remoteMoviesListDataSource.fetchMoviesList() } answers { Result.success(provideMoviesDtosList()) }
        assertThat(repo.fetchMoviesList()).isEqualTo(Result.success(provideDomainModelsList()))
    }

    @Test
    fun returnFailure() = runTest {

        coEvery { remoteMoviesListDataSource.fetchMoviesList() } answers { Result.failure(IOException()) }
        assertThat(repo.fetchMoviesList().isFailure).isTrue()
    }


}