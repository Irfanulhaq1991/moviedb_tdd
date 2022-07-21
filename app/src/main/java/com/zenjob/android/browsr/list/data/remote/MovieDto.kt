package com.zenjob.android.browsr.list.data.remote

import com.squareup.moshi.Json
import java.io.Serializable
import java.util.*
data class MovieDto(
    val id: Long,
    val imdbId: String?,
    val overview: String?,
    val title: String,
    val release_date: String?,
    val vote_average: Float?,
    val poster_path:String?
)

