package com.zenjob.android.browsr

interface Mapper<I, O>{
 fun map(input:I):O
}
