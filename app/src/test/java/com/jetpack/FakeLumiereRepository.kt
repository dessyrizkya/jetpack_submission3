package com.jetpack

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import com.jetpack.data.LumiereDataSource
import com.jetpack.data.source.local.entity.TvShowDetailEntity
import com.jetpack.data.source.local.room.FavoriteMovie
import com.jetpack.data.source.local.room.FavoriteTvShow
import com.jetpack.data.source.remote.response.GenresItem
import com.jetpack.data.source.remote.response.ResultsItem
import com.jetpack.data.source.remote.response.TvResultsItem

class FakeLumiereRepository : LumiereDataSource {

    private val favoriteMovies = mutableListOf<FavoriteMovie>()
    private val favoriteTvShows = mutableListOf<FavoriteTvShow>()

    private val observableFavoriteMovies = MutableLiveData<List<FavoriteMovie>>(favoriteMovies)
    private val observableFavoriteTvShows = MutableLiveData<List<FavoriteTvShow>>(favoriteTvShows)



    override fun getAllMovies(): LiveData<PagingData<ResultsItem>> {
        TODO("Not yet implemented")
    }

    override fun genAllGenres(): LiveData<List<GenresItem>> {
        TODO("Not yet implemented")
    }


    override fun getAllTvshows(): LiveData<PagingData<TvResultsItem>> {
        TODO("Not yet implemented")
    }

    override fun getAllTvGenres(): LiveData<List<GenresItem>> {
        TODO("Not yet implemented")
    }

    override fun getDetailTv(id: Int): LiveData<TvShowDetailEntity> {
        TODO("Not yet implemented")
    }



    private fun refreshMovies() {
        observableFavoriteMovies.postValue(favoriteMovies)
    }

    override fun insertFavMovie(favoriteMovie: FavoriteMovie) {
        favoriteMovies.add(favoriteMovie)
        refreshMovies()
    }

    override fun deleteFavMovie(favoriteMovie: FavoriteMovie) {
        favoriteMovies.remove(favoriteMovie)
        refreshMovies()
    }

    override fun getAllFavMovies(): LiveData<List<FavoriteMovie>> {
        return observableFavoriteMovies
    }

    override fun getFavMovie(id: String): LiveData<List<FavoriteMovie>> {
        TODO("Not yet implemented")
    }


    private fun refreshTvShows() {
        observableFavoriteTvShows.postValue(favoriteTvShows)
    }

    override fun insertFavTvShow(favoriteTvShow: FavoriteTvShow) {
        favoriteTvShows.add(favoriteTvShow)
        refreshTvShows()
    }

    override fun deleteFavTvShow(favoriteTvShow: FavoriteTvShow) {
        favoriteTvShows.remove(favoriteTvShow)
        refreshTvShows()
    }

    override fun getAllFavTvShows(): LiveData<List<FavoriteTvShow>> {
        return observableFavoriteTvShows
    }

    override fun getFavTvShow(id: String): LiveData<List<FavoriteTvShow>> {
        TODO("Not yet implemented")
    }


}