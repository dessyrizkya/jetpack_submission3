package com.jetpack.lumiere.ui.content.favorite.content.tvshow

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.jetpack.lumiere.data.source.LumiereRepository
import com.jetpack.lumiere.data.source.local.room.FavoriteTvShow
import com.jetpack.lumiere.data.source.local.room.LumiereDao
import com.jetpack.lumiere.data.source.local.room.LumiereDatabase

class MyFavoriteTvShowsViewModel(private val lumiereRepository: LumiereRepository) : ViewModel() {

    private lateinit var lumiereDao: LumiereDao

    fun getAllFavorite(application: Application) : LiveData<List<FavoriteTvShow>> {
        val db= LumiereDatabase.getDatabase(application)
        lumiereDao = db.lumiereDao()

        return lumiereRepository.getAllFavTvShows(lumiereDao)
    }
}