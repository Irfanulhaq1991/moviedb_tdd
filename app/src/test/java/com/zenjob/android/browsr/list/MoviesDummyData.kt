package com.zenjob.android.browsr.list

import com.zenjob.android.browsr.list.data.MovieDto
import com.zenjob.android.browsr.list.domain.model.Movie

object MoviesDummyData {

    fun provideDomainModelsList(): List<Movie> {
        return listOf(
            Movie(0, "", "", "", ""),
            Movie(1, "c", "e", "", ""),
            Movie(2, "d", "f", "", ""),
        )
    }

    fun provideMoviesDtosList(): List<MovieDto> {
        return listOf(
            MovieDto(0, "", "", "", null, null, null),
            MovieDto(1, "a", "c", "e", null, null, null),
            MovieDto(2, "b", "d", "f", null, null, null),
        )
    }
}