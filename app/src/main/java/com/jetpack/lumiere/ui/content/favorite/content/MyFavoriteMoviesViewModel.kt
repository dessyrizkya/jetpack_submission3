package com.jetpack.lumiere.ui.content.favorite.content

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.jetpack.lumiere.data.source.LumiereRepository
import com.jetpack.lumiere.data.source.local.room.FavoriteMovie
import com.jetpack.lumiere.data.source.local.room.LumiereDao
import com.jetpack.lumiere.data.source.local.room.LumiereDatabase
import com.jetpack.lumiere.data.source.remote.response.GenresItem

class MyFavoriteMoviesViewModel(private val lumiereRepository: LumiereRepository) : ViewModel() {

    private lateinit var lumiereDao: LumiereDao

    fun getAllFavorite(application: Application) : LiveData<List<FavoriteMovie>> {
        val db= LumiereDatabase.getDatabase(application)
        lumiereDao = db.lumiereDao()

        return lumiereRepository.getAllFavMovies(lumiereDao)
    }
}