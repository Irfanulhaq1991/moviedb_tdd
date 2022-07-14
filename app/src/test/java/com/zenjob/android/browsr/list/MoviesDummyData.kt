package com.zenjob.android.browsr.list

import com.zenjob.android.browsr.list.data.MovieDto
import com.zenjob.android.browsr.list.domain.model.MovieDomainModel

object MoviesDummyData {

    fun provideDomainModelsList(): List<MovieDomainModel> {
        return listOf(
            MovieDomainModel(0, "", "", "", ""),
            MovieDomainModel(1, "c", "e", "", ""),
            MovieDomainModel(2, "d", "f", "", ""),
        )
    }

    fun provideDtoList(): List<MovieDto> {
        return listOf(
            MovieDto(0, "", "", "", null, null, null),
            MovieDto(1, "a", "c", "e", null, null, null),
            MovieDto(2, "b", "d", "f", null, null, null),
        )
    }
}