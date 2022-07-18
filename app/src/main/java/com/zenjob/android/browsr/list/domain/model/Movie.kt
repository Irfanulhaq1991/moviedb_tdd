package com.zenjob.android.browsr.list.domain.model

import com.squareup.moshi.Json
import okhttp3.internal.http.HttpDate.format
import java.io.Serializable
import java.text.DateFormat
import java.util.*

data class Movie(
    val id: Long,
    val overview: String,
    val title: String,
    val imageUrl:String,
    val releaseDate: String,
    val rating:Float,
):Serializable{
    fun formattedReleaseDate():Date{
      return Date()
    }
    fun stringRating():String{
       return rating.toString()
    }

}
