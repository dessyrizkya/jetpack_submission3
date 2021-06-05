package com.jetpack.lumiere.ui.content.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.jetpack.lumiere.data.source.LumiereRepository
import com.jetpack.lumiere.data.source.local.entity.TvShowDetailEntity
import com.jetpack.lumiere.data.source.local.entity.TvShowEntity
import com.jetpack.lumiere.data.source.remote.response.GenresItem
import com.jetpack.lumiere.data.source.remote.response.TvDetail
import com.jetpack.lumiere.data.source.remote.response.TvResultsItem
import com.jetpack.lumiere.utils.DataDummy

class TvShowsViewModel(private val lumiereRepository: LumiereRepository)  : ViewModel() {

    val load = lumiereRepository.isLoading

    fun getTvShows() : LiveData<List<TvShowEntity>> = lumiereRepository.getAllTvShows()

    fun getGenres() : LiveData<List<GenresItem>> = lumiereRepository.genAllTvGenres()

    fun getTvShow(tv_id: Int) : LiveData<TvShowDetailEntity> = lumiereRepository.getTvDetail(tv_id)
}