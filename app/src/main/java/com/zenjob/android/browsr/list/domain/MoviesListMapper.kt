package com.zenjob.android.browsr.list.domain

import com.zenjob.android.browsr.BuildConfig
import com.zenjob.android.browsr.common.Mapper
import com.zenjob.android.browsr.list.data.MovieDto
import com.zenjob.android.browsr.list.data.MoviePageDto
import com.zenjob.android.browsr.list.domain.model.Movie
import com.zenjob.android.browsr.list.domain.model.Movies

class MoviesListMapper: Mapper<MoviePageDto, Movies> {


    override suspend fun map(input: MoviePageDto): Movies {
        return Movies(input.page, input.results.map { Movie(it.id,it.overview?:"",it.title,BuildConfig.TMDB_IMAGE_URL+(it.poster_path?:""),it.release_date?:"",it.vote_average?:0f) })
    }
}
