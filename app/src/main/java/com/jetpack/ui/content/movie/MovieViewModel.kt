package com.jetpack.ui.content.movie

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
class MovieViewModel @Inject constructor(private val lumiereRepository: LumiereRepository) : ViewModel() {

    fun getAllMovies(): LiveData<Resource<PagedList<MovieEntity>>> = lumiereRepository.getAllMovies()
    fun getGenres(): LiveData<List<GenresItem>> = lumiereRepository.getAllGenres()

    fun getDetail(movieId : Int): LiveData<Resource<MovieEntity>> = lumiereRepository.getMovieDetail(movieId)
}