package com.jetpack.data.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.jetpack.data.local.entity.MovieEntity
import com.jetpack.data.local.entity.TvShowEntity
import com.jetpack.data.local.room.LumiereDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val mLumiereDao: LumiereDao) {

    fun getAllMovies() : DataSource.Factory<Int, MovieEntity> = mLumiereDao.getMovies()
    fun getFavMovies() : DataSource.Factory<Int, MovieEntity> = mLumiereDao.getFavoritedMovies()
    fun getMovie(movieId: String) : LiveData<MovieEntity> = mLumiereDao.getMovie(movieId)
    fun insertMovies(movies: List<MovieEntity>) = mLumiereDao.insertMovie(movies)
    fun updateMovie(movie: MovieEntity) = mLumiereDao.updateMovie(movie)
    fun setFavMovie(movie: MovieEntity) {
        movie.isFavorited = !movie.isFavorited
        mLumiereDao.updateMovie(movie)
    }

    fun getAllTvShows() : DataSource.Factory<Int, TvShowEntity> = mLumiereDao.getTvShows()
    fun getFavTvShows() : DataSource.Factory<Int, TvShowEntity> = mLumiereDao.getFavoritedTvs()
    fun getTv(tvId: String) : LiveData<TvShowEntity> = mLumiereDao.getTvShow(tvId)
    fun insertTvShows(tvshows: List<TvShowEntity>) = mLumiereDao.insertTv(tvshows)
    fun updateTvShow(tvshow: TvShowEntity) = mLumiereDao.updateTv(tvshow)
    fun setFavTvshow(tvshow: TvShowEntity) {
        tvshow.isFavorited = !tvshow.isFavorited
        mLumiereDao.updateTv(tvshow)
    }

}