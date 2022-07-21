package com.zenjob.android.browsr.list.data.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.zenjob.android.browsr.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class Networking private constructor(){
    companion object{
        private lateinit var instance: Networking
        fun getInstance(): Networking {
            if(!Companion::instance.isInitialized){
                instance = Networking()
            }
            return instance
        }
    }


    fun provideMoshiConverter(): MoshiConverterFactory {
        return MoshiConverterFactory.create(
            Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
        )
    }

    fun provideRetrofit(basUrl: String,okHttpClient: OkHttpClient,moshi:MoshiConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(basUrl)
            .client(okHttpClient)
            .addConverterFactory(moshi).build()
    }

    fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient().newBuilder().addInterceptor(interceptor).build()
    }

    fun provideBasUrl():String{
        return BuildConfig.TMDB_URL
    }

    fun provideMoviesApi(retrofit: Retrofit): MovieRemoteApi =
        retrofit.create(MovieRemoteApi::class.java)

    fun provideInterceptor(): Interceptor {
        return Interceptor { chain ->

            val original = chain.request()
            val originalHttpUrl = original.url()

            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)
                .build()

            val reqBuilder = original.newBuilder()
                .url(url)
            chain.proceed(reqBuilder.build())
        }
    }
}