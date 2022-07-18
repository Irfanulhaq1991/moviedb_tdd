package com.zenjob.android.browsr.list.di

import com.zenjob.android.browsr.common.Mapper
import com.zenjob.android.browsr.list.data.IMoviesListDataSource
import com.zenjob.android.browsr.list.data.MovieDto
import com.zenjob.android.browsr.list.data.MoviesRepository
import com.zenjob.android.browsr.list.data.RemoteMoviesListDataSource
import com.zenjob.android.browsr.list.domain.FetchingMoviesListUseCase
import com.zenjob.android.browsr.list.domain.MoviesListMapper
import com.zenjob.android.browsr.list.domain.model.Movie
import com.zenjob.android.browsr.list.presentation.MoviesListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module



val movieListModule = module {
    // single instance of HelloRepository
    factory<Mapper<List<MovieDto>, List<Movie>>> { MoviesListMapper() }
    factory<IMoviesListDataSource> { RemoteMoviesListDataSource(get())}

    factory { MoviesRepository(get(),get())}
    factory { FetchingMoviesListUseCase(get())}
    viewModel { MoviesListViewModel(get())}
}



val networkModule = module {
    factory { provideInterceptor()}
    factory { provideOkHttpClient(get()) }
    factory { provideForecastApi(get()) }
    single { provideRetrofit(get()) }
}





