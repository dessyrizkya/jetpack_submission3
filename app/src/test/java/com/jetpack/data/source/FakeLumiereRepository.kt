package com.jetpack.data.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.jetpack.api.Api
import com.jetpack.data.local.LocalDataSource
import com.jetpack.data.local.entity.MovieEntity
import com.jetpack.data.local.entity.TvShowEntity
import com.jetpack.data.source.remote.ApiResponse
import com.jetpack.data.source.remote.RemoteDataSource
import com.jetpack.data.source.remote.response.*
import com.jetpack.vo.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeLumiereRepository @Inject constructor(
    private val api: Api,
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource)
    : LumiereDataSource {

    override fun getAllMovies(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<ResultsItem>>() {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder().apply {
                    setEnablePlaceholders(false)
                    setInitialLoadSizeHint(4)
                    setPageSize(4)
                }.build()
                return LivePagedListBuilder(localDataSource.getAllMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ResultsItem>>> =
                remoteDataSource.getListMovie()

            override fun saveCallResult(data: List<ResultsItem>) {
                val movies = ArrayList<MovieEntity>()
                for (response in data) {

                    val movieEntity = MovieEntity(
                        response.movieId.toString(),
                        response.title,
                        response.description,
                        response.year,
                        "",
                        response.poster,
                        false
                    )
                    movies.add(movieEntity)
                }
                localDataSource.insertMovies(movies)
            }
        }.asLiveData()
    }

    override fun getMovieDetail(movieId : Int): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, MovieDetail>(){
            override fun loadFromDB(): LiveData<MovieEntity> =
                localDataSource.getMovie(movieId.toString())

            override fun shouldFetch(data: MovieEntity?): Boolean =
                data != null && data.genre == ""

            override fun createCall(): LiveData<ApiResponse<MovieDetail>> =
                remoteDataSource.getDetailMovie(movieId.toString())

            override fun saveCallResult(data: MovieDetail) {
                val detail = MovieEntity(
                    data.movieId.toString(),
                    data.title,
                    data.description,
                    data.year,
                    data.genre[0].genre,
                    data.poster,
                    false
                )
                localDataSource.updateMovie(detail)
            }

        }.asLiveData()
    }

    override fun getFavMovies(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder().apply {
            setEnablePlaceholders(false)
            setInitialLoadSizeHint(4)
            setPageSize(4)
        }.build()
        return LivePagedListBuilder(localDataSource.getFavMovies(), config).build()
    }

    override fun getAllTvShows(): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object : NetworkBoundResource<PagedList<TvShowEntity>, List<TvResultsItem>>() {
            override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder().apply {
                    setEnablePlaceholders(false)
                    setInitialLoadSizeHint(4)
                    setPageSize(4)
                }.build()
                return LivePagedListBuilder(localDataSource.getAllTvShows(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvResultsItem>>> =
                remoteDataSource.getListTvShow()

            override fun saveCallResult(data: List<TvResultsItem>) {
                val tvShow = ArrayList<TvShowEntity>()
                for (response in data) {
                    val tvShowEntity = TvShowEntity(
                        response.tvId.toString(),
                        response.title,
                        response.description,
                        response.year,
                        "",
                        response.poster,
                        "",
                        false
                    )
                    tvShow.add(tvShowEntity)
                }
                localDataSource.insertTvShows(tvShow)
            }

        }.asLiveData()
    }

    override fun getTvDetail(tvId: Int): LiveData<Resource<TvShowEntity>> {
        return object : NetworkBoundResource<TvShowEntity, TvDetail>() {
            override fun loadFromDB(): LiveData<TvShowEntity> =
                localDataSource.getTv(tvId.toString())

            override fun shouldFetch(data: TvShowEntity?): Boolean =
                data != null && data.genre == "" && data.episode == ""

            override fun createCall(): LiveData<ApiResponse<TvDetail>> =
                remoteDataSource.getDetailTvShow(tvId.toString())

            override fun saveCallResult(data: TvDetail) {
                val detail = TvShowEntity(
                    data.tvId.toString(),
                    data.title,
                    data.description,
                    data.year,
                    data.genre[0].genre,
                    data.poster,
                    data.eps.toString(),
                    false
                )
                localDataSource.updateTvShow(detail)
            }

        }.asLiveData()
    }

    override fun getFavTvShows(): LiveData<PagedList<TvShowEntity>> {
        val config = PagedList.Config.Builder().apply {
            setEnablePlaceholders(false)
            setInitialLoadSizeHint(4)
            setPageSize(4)
        }.build()
        return LivePagedListBuilder(localDataSource.getFavTvShows(), config).build()
    }

    override fun getAllGenres(): LiveData<List<GenresItem>> {
        val _genres = MutableLiveData<List<GenresItem>>()
        val client = api.getGenres(RemoteDataSource.apiKey, RemoteDataSource.language)
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

    override fun setFavMovie(movie: MovieEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            localDataSource.setFavMovie(movie)
        }
    }

    override fun setFavTvShow(tv: TvShowEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            localDataSource.setFavTvshow(tv)
        }
    }
}