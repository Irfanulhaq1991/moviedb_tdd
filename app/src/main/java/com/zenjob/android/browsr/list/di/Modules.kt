package com.zenjob.android.browsr.list.di

import com.zenjob.android.browsr.common.Mapper
import com.zenjob.android.browsr.list.data.*
import com.zenjob.android.browsr.list.domain.FetchingMoviesListUseCase
import com.zenjob.android.browsr.list.domain.MoviesListMapper
import com.zenjob.android.browsr.list.domain.model.Movies
import com.zenjob.android.browsr.list.presentation.MoviesListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module



val movieListModule = module {
    // single instance of HelloRepository
    factory<Mapper<MoviePageDto, Movies>> { MoviesListMapper() }
    factory<IMoviesListDataSource> { RemoteMoviesListDataSource(get())}

    factory { MoviesRepository(get(),get())}
    factory { FetchingMoviesListUseCase(get())}
    viewModel { MoviesListViewModel(get())}
}



val networkModule = module {
    factory { provideInterceptor()}
    factory { provideMoshiConverter() }
    factory { provideOkHttpClient(get()) }
    factory { provideForecastApi(get()) }
    single { provideRetrofit(get(),get()) }
}





