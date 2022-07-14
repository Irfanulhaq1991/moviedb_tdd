package com.zenjob.android.browsr.list.domain.model

import com.squareup.moshi.Json

data class MoviesPageAggregateModel(
    val pageId: Int,
    val results: List<MovieDomainModel>
)
