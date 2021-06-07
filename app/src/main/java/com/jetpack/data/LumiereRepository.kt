package com.jetpack.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.jetpack.Api.Api
import com.jetpack.data.source.local.entity.TvShowDetailEntity
import com.jetpack.data.source.local.room.FavoriteMovie
import com.jetpack.data.source.local.room.FavoriteTvShow
import com.jetpack.data.source.local.room.LumiereDao
import com.jetpack.data.source.remote.MoviePagingSource
import com.jetpack.data.source.remote.TvshowPagingSource
import com.jetpack.data.source.remote.response.GenreResponse
import com.jetpack.data.source.remote.response.GenresItem
import com.jetpack.data.source.remote.response.TvDetail
import com.jetpack.data.source.remote.response.TvResultsItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class LumiereRepository @Inject constructor(private val api: Api, private val dao: LumiereDao) : LumiereDataSource {

    override fun getAllMovies() =
        Pager(config = PagingConfig(
            pageSize = 5,
            maxSize = 20,
            enablePlaceholders = false),
            pagingSourceFactory = {
                MoviePagingSource(api)
            }).liveData

    override fun genAllGenres(): LiveData<List<GenresItem>> {
        val _genres = MutableLiveData<List<GenresItem>>()
        val client = api.getGenres()
        client.enqueue(object : Callback<GenreResponse> {
            override fun onResponse(call: Call<GenreResponse>, response: Response<GenreResponse>) {
                if (response.isSuccessful) {
                    _genres.value = response.body()?.genres
                } else {
                    Log.e("Genre Repository", "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<GenreResponse>, t: Throwable) {
                Log.e("Genre Repository", "onFailure: ${t.message.toString()}")

            }
        })

        val genres : LiveData<List<GenresItem>> = _genres
        return genres
    }

    override fun getAllTvshows() =
        Pager(config = PagingConfig(
            pageSize = 5,
            maxSize = 20,
            enablePlaceholders = false),
            pagingSourceFactory = {
                TvshowPagingSource(api)
            }).liveData

    override fun getAllTvGenres(): LiveData<List<GenresItem>> {
        val _genres = MutableLiveData<List<GenresItem>>()
        val client = api.getTvGenres()
        client.enqueue(object : Callback<GenreResponse> {
            override fun onResponse(call: Call<GenreResponse>, response: Response<GenreResponse>) {
                if (response.isSuccessful) {
                    _genres.value = response.body()?.genres
                } else {
                    Log.e("Genre Repository", "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<GenreResponse>, t: Throwable) {
                Log.e("Genre Repository", "onFailure: ${t.message.toString()}")

            }
        })

        val genres : LiveData<List<GenresItem>> = _genres
        return genres
    }

    override fun getDetailTv(id: Int): LiveData<TvShowDetailEntity> {
        val tv = MutableLiveData<TvShowDetailEntity>()
        CoroutineScope(IO).launch {
            val client = api.getTvDetail(id)
            val detail = TvShowDetailEntity(
                client.tvId.toString(),
                client.title,
                client.description,
                client.year,
                client.genre[0].genre,
                client.poster,
                client.eps.toString()
            )
            tv.postValue(detail)
        }

        return tv
    }


    fun insertFavMovie(favoriteMovie: FavoriteMovie) =
        dao.insertMovie(favoriteMovie)

    fun deleteFavMovie(favoriteMovie: FavoriteMovie) =
        dao.deleteMovie(favoriteMovie)

    fun getAllFavMovies() : LiveData<List<FavoriteMovie>> = dao.getAllMovies()

    fun getFavMovie(id: String) : LiveData<List<FavoriteMovie>> = dao.getMovieById(id)


    fun insertFavTvShow(favoriteTvShow: FavoriteTvShow) =
        dao.insertTvShow(favoriteTvShow)

    fun deleteFavTvShow(favoriteTvShow: FavoriteTvShow) =
        dao.deleteTvShow(favoriteTvShow)

    fun getAllFavTvShows() : LiveData<List<FavoriteTvShow>> = dao.getAllTvShows()

    fun getFavTvShow(id: String) : LiveData<List<FavoriteTvShow>> = dao.getTvshowById(id)
}