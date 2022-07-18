package com.zenjob.android.browsr.list

import com.zenjob.android.browsr.list.di.movieListModule
import com.zenjob.android.browsr.list.di.networkModule
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.koin.test.mock.MockProviderRule
import org.mockito.Mockito

class MoviesListDependenciesCheckingTest : KoinTest {

    // Declare Mock with Mockito
    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        Mockito.mock(clazz.java)
    }

    // verify the Koin configuration
    @Test
    fun checkAllModules() = checkModules {
        modules(movieListModule, networkModule)
    }
}