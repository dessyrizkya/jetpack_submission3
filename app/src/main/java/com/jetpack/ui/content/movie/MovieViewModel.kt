package com.jetpack.ui.content.movie

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetpack.data.LumiereRepository
import com.jetpack.data.source.local.room.FavoriteMovie
import com.jetpack.data.source.remote.response.GenresItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val lumiereRepository: LumiereRepository) : ViewModel() {
    val movies = lumiereRepository.getAllMovies()

    fun getGenres() : LiveData<List<GenresItem>> = lumiereRepository.genAllGenres()

    fun insert(favoriteMovie: FavoriteMovie) {
        viewModelScope.launch(Dispatchers.IO) {
            lumiereRepository.insertFavMovie(favoriteMovie)
        }
    }

    fun delete(favoriteMovie: FavoriteMovie) {
        viewModelScope.launch(Dispatchers.IO) {
            lumiereRepository.deleteFavMovie(favoriteMovie)
        }
    }

    fun isFavorited(id: String) : LiveData<List<FavoriteMovie>> = lumiereRepository.getFavMovie(id)
}