package com.zenjob.android.browsr.list

import com.zenjob.android.browsr.list.data.MovieDto
import com.zenjob.android.browsr.list.data.MoviePageDto
import com.zenjob.android.browsr.list.domain.model.Movie
import com.zenjob.android.browsr.list.domain.model.Movies

object MoviesDummyData {

    fun provideDomainModelsList(): Movies {
        return Movies(
            1,
            listOf(
                Movie(0, "", "", "https://image.tmdb.org/t/p/original", "",0f),
                Movie(1, "c", "e", "https://image.tmdb.org/t/p/original", "",0f),
                Movie(2, "d", "f", "https://image.tmdb.org/t/p/original", "",0f),
            )
        )

    }

    fun provideMoviesDtosList(): MoviePageDto {
        return MoviePageDto(
            1, 1, 1,
            listOf(
                MovieDto(0, "", "", "", null, null, null),
                MovieDto(1, "a", "c", "e", null, null, null),
                MovieDto(2, "b", "d", "f", null, null, null),
            )
        )

    }
}