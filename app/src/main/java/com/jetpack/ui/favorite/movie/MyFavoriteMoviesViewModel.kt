package com.jetpack.ui.favorite.movie

import androidx.lifecycle.ViewModel
import com.jetpack.data.LumiereRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyFavoriteMoviesViewModel @Inject constructor(private val lumiereRepository: LumiereRepository) : ViewModel(){
    val movies = lumiereRepository.getAllFavMovies()
}