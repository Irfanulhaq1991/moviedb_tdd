package com.zenjob.android.browsr.list.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.zenjob.android.browsr.R


class ListActivity : AppCompatActivity()//, MovieListAdapter.OnItemClickListener
{

//    fun fetchMovies() {
//
//        val moshi = Moshi.Builder()
//            .add(KotlinJsonAdapterFactory())
//            .add(DateJsonAdapter())
//            .build()
//
//        val tmdbApiInterceptor = Interceptor { chain ->
//
//            val original = chain.request()
//            val originalHttpUrl = original.url()
//
//            val url = originalHttpUrl.newBuilder()
//                .addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)
//                .build()
//
//            val reqBuilder = original.newBuilder()
//                .url(url)
//            chain.proceed(reqBuilder.build())
//        }
//
//        val okHttpClient = OkHttpClient.Builder()
//            .addInterceptor(tmdbApiInterceptor)
//            .build()
//
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://api.themoviedb.org/3/")
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .addConverterFactory(MoshiConverterFactory.create(moshi))
//            .client(okHttpClient)
//            .build()
//
//        retrofit.create(TMDBApi::class.java).getPopularTvShows()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe (
//                { paginatedList ->
//                    mAdapter.submitList(paginatedList.results)
//                },{
//                    Toast.makeText(this,"Error Occurred",Toast.LENGTH_SHORT).show()
//                }
//            )
//    }

    //val mAdapter = MovieListAdapter().apply { listener = this@ListActivity }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        val list: RecyclerView = findViewById(R.id.list)

       // list.adapter = mAdapter
//
//        fetchMovies()
//
//        val refresh = findViewById<View>(R.id.refresh)
//        refresh.setOnClickListener {
//            fetchMovies()
//        }
    }

//    override fun onMovieItemClick(
//        itemView: View,
//        position: Int,
//        movie: Movie
//    ) {
//        val intent = Intent(this, DetailActivity::class.java)
//        intent.putExtra("movie", movie)
//        startActivity(intent)
//    }

}