package com.jetpack.lumiere.data.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jetpack.lumiere.api.ApiConfig
import com.jetpack.lumiere.data.source.local.entity.MovieEntity
import com.jetpack.lumiere.data.source.local.entity.TvShowDetailEntity
import com.jetpack.lumiere.data.source.local.entity.TvShowEntity
import com.jetpack.lumiere.data.source.remote.RemoteDataSource
import com.jetpack.lumiere.data.source.remote.response.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FakeLumiereRepository(private val remoteDataSource: RemoteDataSource): LumiereDataSource {

    private val TAG = "MoviesViewModel"
    val api_key = "2c5873fb96074b9507457be681dc065d"
    private val language = "en-US"

    override fun getAllMovies(): LiveData<List<MovieEntity>> {
        val _movies = MutableLiveData<List<MovieEntity>>()

        CoroutineScope(IO).launch {
            remoteDataSource.getListMovie(object : RemoteDataSource.LoadMoviesCallback {
                override fun onMoviesReceived(movieResponse: List<ResultsItem>) {
                    val movie = ArrayList<MovieEntity>()
                    for (response in movieResponse) {
                        val movieEntity = MovieEntity(
                            response.movieId.toString(),
                            response.title,
                            response.description,
                            response.year,
                            response.genre[0].toString(),
                            response.poster
                        )
                        movie.add(movieEntity)
                    }
                    _movies.postValue(movie)

                }
            })
        }

        return _movies
    }

    override fun getMovieDetail(movieId : Int): LiveData<MovieEntity> {
        val _detailMovie = MutableLiveData<MovieEntity>()

        CoroutineScope(IO).launch {
            remoteDataSource.getDetailMovie(movieId, object : RemoteDataSource.LoadMovieDetailCallback {
                override fun onMovieDetailReceived(movieDetailResponse: MovieDetail) {
                    val detail : MovieEntity
                    detail = MovieEntity(
                        movieDetailResponse.movieId.toString(),
                        movieDetailResponse.title,
                        movieDetailResponse.description,
                        movieDetailResponse.year,
                        movieDetailResponse.genre[0].genre,
                        movieDetailResponse.poster
                    )
                    _detailMovie.postValue(detail)
                }
            })
        }

        return _detailMovie
    }

    override fun getAllTvShows(): LiveData<List<TvShowEntity>> {

        val _tvshows = MutableLiveData<List<TvShowEntity>>()

        CoroutineScope(IO).launch {
            remoteDataSource.getListTvShow(object : RemoteDataSource.LoadTvShowCallback {
                override fun onTvShowsReceived(tvResponse: List<TvResultsItem>) {
                    val tvShow = ArrayList<TvShowEntity>()
                    for (response in tvResponse) {
                        val tvShowEntity = TvShowEntity(
                            response.tvId.toString(),
                            response.title,
                            response.description,
                            response.year,
                            response.genreId[0].toString(),
                            response.poster
                        )
                        tvShow.add(tvShowEntity)
                    }
                    _tvshows.postValue(tvShow)
                }
            })
        }

        return _tvshows
    }

    override fun getTvDetail(tvId: Int): LiveData<TvShowDetailEntity> {

        val _detailTv = MutableLiveData<TvShowDetailEntity>()

        CoroutineScope(IO).launch {
            remoteDataSource.getDetailTvShow(tvId, object : RemoteDataSource.LoadTvShowDetailCallback{
                override fun onTvShowDetailReceived(tvDetailResponse: TvDetail) {
                    val detail : TvShowDetailEntity
                    detail = TvShowDetailEntity(
                        tvDetailResponse.tvId.toString(),
                        tvDetailResponse.title,
                        tvDetailResponse.description,
                        tvDetailResponse.year,
                        tvDetailResponse.genre[0].genre,
                        tvDetailResponse.poster,
                        tvDetailResponse.eps.toString()
                    )
                    _detailTv.postValue(detail)
                }

            })
        }
        return _detailTv
    }

    override fun genAllGenres(): LiveData<List<GenresItem>> {
        val _genres = MutableLiveData<List<GenresItem>>()
        val client = ApiConfig.instance.getGenres(api_key, language)
        client.enqueue(object : Callback<GenreResponse>{
            override fun onResponse(call: Call<GenreResponse>, response: Response<GenreResponse>) {
                if (response.isSuccessful) {
                    _genres.value = response.body()?.genres
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<GenreResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })

        val genres : LiveData<List<GenresItem>> = _genres
        return genres
    }

    override fun genAllTvGenres(): LiveData<List<GenresItem>> {
        val _genres = MutableLiveData<List<GenresItem>>()
        val client = ApiConfig.instance.getTvGenres(api_key, language)
        client.enqueue(object : Callback<GenreResponse>{
            override fun onResponse(call: Call<GenreResponse>, response: Response<GenreResponse>) {
                if (response.isSuccessful) {
                    _genres.value = response.body()?.genres
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<GenreResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })

        val genres : LiveData<List<GenresItem>> = _genres
        return genres
    }
}