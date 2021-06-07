package com.jetpack.data

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.jetpack.data.source.local.entity.TvShowDetailEntity
import com.jetpack.data.source.remote.response.GenresItem
import com.jetpack.data.source.remote.response.ResultsItem
import com.jetpack.data.source.remote.response.TvResultsItem

interface LumiereDataSource {

    fun getAllMovies() : LiveData<PagingData<ResultsItem>>

    fun genAllGenres(): LiveData<List<GenresItem>>

    fun getAllTvshows() : LiveData<PagingData<TvResultsItem>>

    fun getAllTvGenres(): LiveData<List<GenresItem>>

    fun getDetailTv(id : Int): LiveData<TvShowDetailEntity>

}