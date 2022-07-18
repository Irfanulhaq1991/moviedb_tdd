package com.zenjob.android.browsr.list.domain.model

import com.squareup.moshi.Json
import java.io.Serializable
import java.util.*

data class Movie(
    val id: Long,
    val overview: String,
    val title: String,
    val imageUrl:String,
    val releaseDate: String
){
    fun formattedReleaseDate():Date{
        return Date()
    }
}
