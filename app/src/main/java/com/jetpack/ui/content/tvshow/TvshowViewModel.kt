package com.jetpack.ui.content.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetpack.data.LumiereRepository
import com.jetpack.data.source.local.entity.TvShowDetailEntity
import com.jetpack.data.source.local.room.FavoriteMovie
import com.jetpack.data.source.local.room.FavoriteTvShow
import com.jetpack.data.source.remote.response.GenresItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvshowViewModel @Inject constructor(private val lumiereRepository: LumiereRepository) : ViewModel() {

    val tvshows = lumiereRepository.getAllTvshows()

    fun getGenres() : LiveData<List<GenresItem>> = lumiereRepository.getAllTvGenres()

    fun getTvShow(tv_id: Int) : LiveData<TvShowDetailEntity> = lumiereRepository.getDetailTv(tv_id)

    fun insert(favoriteTvShow: FavoriteTvShow) {
        viewModelScope.launch(Dispatchers.IO) {
            lumiereRepository.insertFavTvShow(favoriteTvShow)
        }
    }

    fun delete(favoriteTvShow: FavoriteTvShow) {
        viewModelScope.launch(Dispatchers.IO) {
            lumiereRepository.deleteFavTvShow(favoriteTvShow)
        }
    }

    fun isFavorited(id: String) : LiveData<List<FavoriteTvShow>> = lumiereRepository.getFavTvShow(id)


}