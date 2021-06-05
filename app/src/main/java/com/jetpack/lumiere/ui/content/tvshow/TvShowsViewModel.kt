package com.jetpack.lumiere.ui.content.tvshow

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetpack.lumiere.data.source.LumiereRepository
import com.jetpack.lumiere.data.source.local.entity.TvShowDetailEntity
import com.jetpack.lumiere.data.source.local.entity.TvShowEntity
import com.jetpack.lumiere.data.source.local.room.FavoriteMovie
import com.jetpack.lumiere.data.source.local.room.FavoriteTvShow
import com.jetpack.lumiere.data.source.local.room.LumiereDao
import com.jetpack.lumiere.data.source.local.room.LumiereDatabase
import com.jetpack.lumiere.data.source.remote.response.GenresItem
import com.jetpack.lumiere.data.source.remote.response.TvDetail
import com.jetpack.lumiere.data.source.remote.response.TvResultsItem
import com.jetpack.lumiere.utils.DataDummy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TvShowsViewModel(private val lumiereRepository: LumiereRepository)  : ViewModel() {

    private lateinit var lumiereDao: LumiereDao

    val load = lumiereRepository.isLoading

    fun getTvShows() : LiveData<List<TvShowEntity>> = lumiereRepository.getAllTvShows()

    fun getGenres() : LiveData<List<GenresItem>> = lumiereRepository.genAllTvGenres()

    fun getTvShow(tv_id: Int) : LiveData<TvShowDetailEntity> = lumiereRepository.getTvDetail(tv_id)

    fun insert(application: Application, favoriteTvShow: FavoriteTvShow) {
        val db= LumiereDatabase.getDatabase(application)
        lumiereDao = db.lumiereDao()

        viewModelScope.launch(Dispatchers.IO) {
            lumiereRepository.insertFavTvShow(favoriteTvShow, lumiereDao)
        }
    }

    fun delete(application: Application, favoriteTvShow: FavoriteTvShow) {
        val db= LumiereDatabase.getDatabase(application)
        lumiereDao = db.lumiereDao()

        viewModelScope.launch(Dispatchers.IO) {
            lumiereRepository.deleteFavTvShow(favoriteTvShow, lumiereDao)
        }
    }

    fun isFavorited(application: Application, id: String) : LiveData<List<FavoriteTvShow>> {
        val db= LumiereDatabase.getDatabase(application)
        lumiereDao = db.lumiereDao()

        return lumiereRepository.getFavTvShow(lumiereDao, id)
    }
}