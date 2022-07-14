package com.zenjob.android.browsr.list.domain

import com.zenjob.android.browsr.list.data.MovieDto
import com.zenjob.android.browsr.list.domain.model.MovieDomainModel

class MoviesListMapper{
    fun map(input: List<MovieDto>): List<MovieDomainModel> {
        return input.map { MovieDomainModel(it.id,it.overview?:"",it.title,it.poster_path?:"",it.release_date?:"") }
    }

}
