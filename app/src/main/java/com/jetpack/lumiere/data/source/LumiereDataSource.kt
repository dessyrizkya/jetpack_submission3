package com.jetpack.lumiere.data.source

import androidx.lifecycle.LiveData
import com.jetpack.lumiere.data.source.local.entity.MovieEntity
import com.jetpack.lumiere.data.source.local.entity.TvShowDetailEntity
import com.jetpack.lumiere.data.source.local.entity.TvShowEntity
import com.jetpack.lumiere.data.source.remote.response.*

interface LumiereDataSource {

    fun getAllMovies(): LiveData<List<MovieEntity>>

    fun getMovieDetail(movieId : Int): LiveData<MovieEntity>

    fun getAllTvShows(): LiveData<List<TvShowEntity>>

    fun getTvDetail(tvId : Int): LiveData<TvShowDetailEntity>

    fun genAllGenres(): LiveData<List<GenresItem>>

    fun genAllTvGenres(): LiveData<List<GenresItem>>
}
