package com.zenjob.android.browsr

interface Mapper<I, O>{
 suspend fun map(input:I):O
}
