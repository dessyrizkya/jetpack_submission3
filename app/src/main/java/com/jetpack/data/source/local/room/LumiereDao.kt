package com.jetpack.data.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.jetpack.data.local.entity.MovieEntity
import com.jetpack.data.local.entity.TvShowEntity

@Dao
interface LumiereDao {

    @Query("SELECT * FROM movieentities")
    fun getMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM movieentities where movieId like :id")
    fun getMovie(id:String): LiveData<MovieEntity>

    @Query("SELECT * FROM movieentities where isFavorited = 1")
    fun getFavoritedMovies(): DataSource.Factory<Int, MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movies: List<MovieEntity>)

    @Update
    fun updateMovie(movie: MovieEntity)



    @Query("SELECT * FROM tvshowentities")
    fun getTvShows(): DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM tvshowentities where tvId like :id")
    fun getTvShow(id: String): LiveData<TvShowEntity>

    @Query("SELECT * FROM tvshowentities where isFavorited = 1")
    fun getFavoritedTvs(): DataSource.Factory<Int, TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTv(tvshow: List<TvShowEntity>)

    @Update
    fun updateTv(tvshow: TvShowEntity)

}