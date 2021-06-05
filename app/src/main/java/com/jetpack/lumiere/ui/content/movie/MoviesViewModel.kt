package com.jetpack.lumiere.ui.content.movie

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.RoomDatabase
import com.jetpack.lumiere.data.source.LumiereRepository
import com.jetpack.lumiere.data.source.local.entity.MovieEntity
import com.jetpack.lumiere.data.source.local.room.FavoriteMovie
import com.jetpack.lumiere.data.source.local.room.LumiereDao
import com.jetpack.lumiere.data.source.local.room.LumiereDatabase
import com.jetpack.lumiere.data.source.remote.response.GenresItem
import com.jetpack.lumiere.data.source.remote.response.MovieDetail
import com.jetpack.lumiere.data.source.remote.response.ResultsItem
import com.jetpack.lumiere.utils.DataDummy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoviesViewModel(private val lumiereRepository: LumiereRepository) : ViewModel() {

    private lateinit var lumiereDao: LumiereDao

    fun getMovies() : LiveData<List<MovieEntity>> = lumiereRepository.getAllMovies()

    fun getGenres() : LiveData<List<GenresItem>> = lumiereRepository.genAllGenres()

    fun getMovie(movieId : Int) : LiveData<MovieEntity> = lumiereRepository.getMovieDetail(movieId)

    fun insert(application: Application, favoriteMovie: FavoriteMovie) {
        val db= LumiereDatabase.getDatabase(application)
        lumiereDao = db.lumiereDao()

        viewModelScope.launch(Dispatchers.IO) {
            lumiereRepository.insertFavMovie(favoriteMovie, lumiereDao)
        }
    }

    fun delete(application: Application, favoriteMovie: FavoriteMovie) {
        val db= LumiereDatabase.getDatabase(application)
        lumiereDao = db.lumiereDao()

        viewModelScope.launch(Dispatchers.IO) {
            lumiereRepository.deleteFavMovie(favoriteMovie, lumiereDao)
        }
    }

    fun isFavorited(application: Application, id: String) : LiveData<List<FavoriteMovie>> {
        val db= LumiereDatabase.getDatabase(application)
        lumiereDao = db.lumiereDao()

        return lumiereRepository.getFavMovie(lumiereDao, id)
    }
}