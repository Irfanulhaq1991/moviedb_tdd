package com.zenjob.android.browsr.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.zenjob.android.browsr.BR
import com.zenjob.android.browsr.R
import com.zenjob.android.browsr.databinding.ActivityDetailBinding

const val EXTRA_KEY = "movie"
class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       val binding = DataBindingUtil.setContentView<ActivityDetailBinding>(this,R.layout.activity_detail)
        val movie =  intent.getSerializableExtra(EXTRA_KEY)
        binding.setVariable(BR.movie,movie)

    }

}
