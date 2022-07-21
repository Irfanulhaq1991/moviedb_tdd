package com.zenjob.android.browsr.list.di

import com.zenjob.android.browsr.common.Mapper
import com.zenjob.android.browsr.list.data.remote.*
import com.zenjob.android.browsr.list.domain.FetchingMoviesListUseCase
import com.zenjob.android.browsr.list.domain.MoviesListMapper
import com.zenjob.android.browsr.list.domain.model.Movies
import com.zenjob.android.browsr.list.presentation.MoviesListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module



val movieListModule = module {
    // single instance of HelloRepository
    factory<Mapper<MoviePageDto, Movies>> { MoviesListMapper() }
    factory<IMoviesListDataSource> { RemoteMoviesListDataSource(get()) }

    factory { MoviesRepository(get(),get()) }
    factory { FetchingMoviesListUseCase(get())}
    viewModel { MoviesListViewModel(get())}
}



val networkModule = module {
    val networking = Networking.getInstance()
    factory { networking.provideInterceptor()}
    factory { networking.provideMoshiConverter() }
    factory { networking.provideOkHttpClient(get()) }
    factory { networking.provideMoviesApi(get()) }
    factory { networking.provideBasUrl() }
    single { networking.provideRetrofit(get(),get(),get()) }
}





