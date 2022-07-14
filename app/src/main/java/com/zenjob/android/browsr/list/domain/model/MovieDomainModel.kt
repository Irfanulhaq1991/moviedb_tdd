package com.zenjob.android.browsr.list.domain.model

import com.squareup.moshi.Json
import java.io.Serializable
import java.util.*

data class MovieDomainModel(
    val id: Long,
    val overview: String?,
    val title: String,
    val imageUrl:String,
    private val releaseDate: String
){
    fun getReleaseDate():Date{
        return Date()
    }
}
