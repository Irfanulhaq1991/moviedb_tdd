package com.zenjob.android.browsr.common

interface Mapper<I, O>{
 suspend fun map(input:I):O
}
