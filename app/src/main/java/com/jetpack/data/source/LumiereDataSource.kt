package com.jetpack.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.jetpack.data.local.entity.MovieEntity
import com.jetpack.data.local.entity.TvShowEntity
import com.jetpack.data.source.remote.response.GenresItem
import com.jetpack.data.source.remote.response.MovieDetail
import com.jetpack.data.source.remote.response.TvDetail
import com.jetpack.vo.Resource

interface LumiereDataSource {
    fun getAllMovies(): LiveData<Resource<PagedList<MovieEntity>>>
    fun getMovieDetail(movieId : Int): LiveData<Resource<MovieEntity>>
    fun getFavMovies(): LiveData<PagedList<MovieEntity>>

    fun getAllTvShows(): LiveData<Resource<PagedList<TvShowEntity>>>
    fun getTvDetail(tvId : Int): LiveData<Resource<TvShowEntity>>
    fun getFavTvShows(): LiveData<PagedList<TvShowEntity>>

    fun getAllGenres(): LiveData<List<GenresItem>>

    fun setFavMovie(movie: MovieEntity)
    fun setFavTvShow(tv: TvShowEntity)
}