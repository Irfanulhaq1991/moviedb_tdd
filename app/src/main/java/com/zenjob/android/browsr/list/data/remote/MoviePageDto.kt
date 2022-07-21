package com.zenjob.android.browsr.list.data.remote

data class MoviePageDto(
    val page: Int,
    val total_results: Int,
    val total_pages: Int,
    val results: List<MovieDto>
)
