package com.zenjob.android.browsr.list

import com.google.common.truth.Truth.assertThat
import com.zenjob.android.browsr.list.domain.MoviesListMapper
import junit.framework.TestCase
import org.junit.Test

class MoviesListMapperShould {


    @Test
    fun returnListOfSizeInput(){
        val input = MoviesDummyData.provideDtoList()
        val mapper = MoviesListMapper()
        val output = mapper.map(input)
        assertThat(output.size).isEqualTo(input.size)
    }
    @Test
    fun returnListOfDomainModels(){
        val input = MoviesDummyData.provideDtoList()
        val expected = MoviesDummyData.provideDomainModelsList()
        val mapper = MoviesListMapper()
        val output = mapper.map(input)
        assertThat(output).isEqualTo(expected)
    }
}