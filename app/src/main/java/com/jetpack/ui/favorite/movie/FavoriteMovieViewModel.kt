package com.jetpack.ui.favorite.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.jetpack.data.local.entity.MovieEntity
import com.jetpack.data.source.LumiereRepository
import com.jetpack.data.source.remote.response.GenresItem
import com.jetpack.vo.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteMovieViewModel@Inject constructor(private val lumiereRepository: LumiereRepository) : ViewModel() {
    fun getFavMovies(): LiveData<PagedList<MovieEntity>> = lumiereRepository.getFavMovies()
    fun getGenres(): LiveData<List<GenresItem>> = lumiereRepository.getAllGenres()
}