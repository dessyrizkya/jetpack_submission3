package com.jetpack.ui.favorite.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.jetpack.data.local.entity.TvShowEntity
import com.jetpack.data.source.LumiereRepository
import com.jetpack.data.source.remote.response.GenresItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteTvshowViewModel@Inject constructor(private val lumiereRepository: LumiereRepository) : ViewModel() {
    fun getFavTvshows(): LiveData<PagedList<TvShowEntity>> = lumiereRepository.getFavTvShows()
    fun getGenres(): LiveData<List<GenresItem>> = lumiereRepository.getAllGenres()
}