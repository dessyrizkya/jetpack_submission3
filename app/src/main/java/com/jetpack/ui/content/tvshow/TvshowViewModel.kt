package com.jetpack.ui.content.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.jetpack.data.local.entity.TvShowEntity
import com.jetpack.data.source.LumiereRepository
import com.jetpack.data.source.remote.response.GenresItem
import com.jetpack.vo.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TvShowViewModel @Inject constructor(private val lumiereRepository: LumiereRepository) : ViewModel() {

    fun getAllTvShows(): LiveData<Resource<PagedList<TvShowEntity>>> = lumiereRepository.getAllTvShows()
    fun getGenres(): LiveData<List<GenresItem>> = lumiereRepository.getAllGenres()
    fun setFav(tvshow: TvShowEntity) {
        lumiereRepository.setFavTvShow(tvshow)
    }

    fun getDetail(tvId: Int) : LiveData<Resource<TvShowEntity>> = lumiereRepository.getTvDetail(tvId)

}