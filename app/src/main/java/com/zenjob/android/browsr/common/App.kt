package com.zenjob.android.browsr.common

import android.app.Application
import com.zenjob.android.browsr.list.di.movieListModule
import com.zenjob.android.browsr.list.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            // Koin Android logger
            androidLogger()
            //inject Android context
            androidContext(this@App)
            // use modules
            modules(movieListModule, networkModule)
        }

    }
}