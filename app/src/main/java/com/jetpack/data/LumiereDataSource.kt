package com.jetpack.data

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.jetpack.data.source.local.entity.TvShowDetailEntity
import com.jetpack.data.source.local.room.FavoriteMovie
import com.jetpack.data.source.local.room.FavoriteTvShow
import com.jetpack.data.source.remote.response.GenresItem
import com.jetpack.data.source.remote.response.ResultsItem
import com.jetpack.data.source.remote.response.TvResultsItem

interface LumiereDataSource {

    fun getAllMovies() : LiveData<PagingData<ResultsItem>>

    fun genAllGenres(): LiveData<List<GenresItem>>

    fun getAllTvshows() : LiveData<PagingData<TvResultsItem>>

    fun getAllTvGenres(): LiveData<List<GenresItem>>

    fun getDetailTv(id : Int): LiveData<TvShowDetailEntity>



    fun insertFavMovie(favoriteMovie: FavoriteMovie)

    fun deleteFavMovie(favoriteMovie: FavoriteMovie)

    fun getAllFavMovies() : LiveData<List<FavoriteMovie>>

    fun getFavMovie(id: String) : LiveData<List<FavoriteMovie>>


    fun insertFavTvShow(favoriteTvShow: FavoriteTvShow)

    fun deleteFavTvShow(favoriteTvShow: FavoriteTvShow)

    fun getAllFavTvShows() : LiveData<List<FavoriteTvShow>>

    fun getFavTvShow(id: String) : LiveData<List<FavoriteTvShow>>

}