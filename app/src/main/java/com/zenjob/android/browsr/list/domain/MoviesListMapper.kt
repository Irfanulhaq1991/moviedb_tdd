package com.zenjob.android.browsr.list.domain

import com.zenjob.android.browsr.Mapper
import com.zenjob.android.browsr.list.data.MovieDto
import com.zenjob.android.browsr.list.domain.model.Movie

class MoviesListMapper: Mapper<List<MovieDto>, List<Movie>> {
   override fun map(input: List<MovieDto>): List<Movie> {
        return input.map { Movie(it.id,it.overview?:"",it.title,it.poster_path?:"",it.release_date?:"") }
    }

}
