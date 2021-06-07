package com.jetpack.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface LumiereDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMovie(favoriteMovie: FavoriteMovie)

    @Delete
    fun deleteMovie(favoriteMovie: FavoriteMovie)

    @Query("SELECT * from FavoriteMovie")
    fun getAllMovies(): LiveData<List<FavoriteMovie>>

    @Query("SELECT * from FavoriteMovie where id like :id")
    fun getMovieById(id: String): LiveData<List<FavoriteMovie>>



    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTvShow(favoriteTvShow: FavoriteTvShow)

    @Delete
    fun deleteTvShow(favoriteTvShow: FavoriteTvShow)

    @Query("SELECT * from FavoriteTvShow")
    fun getAllTvShows(): LiveData<List<FavoriteTvShow>>

    @Query("SELECT * from FavoriteTvShow where id like :id")
    fun getTvshowById(id: String): LiveData<List<FavoriteTvShow>>
}