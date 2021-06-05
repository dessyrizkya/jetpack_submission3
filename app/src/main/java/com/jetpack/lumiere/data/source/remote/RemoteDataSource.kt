package com.jetpack.lumiere.data.source.remote

import com.jetpack.lumiere.api.ApiConfig
import com.jetpack.lumiere.data.source.remote.response.*
import com.jetpack.lumiere.utils.EspressoIdlingResource
import retrofit2.await

class RemoteDataSource {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource = instance ?: synchronized(this) {
            RemoteDataSource().apply {
                instance = this
            }
        }
    }

    val api_key = "2c5873fb96074b9507457be681dc065d"
    private val language = "en-US"

    suspend fun getListMovie(callback: LoadMoviesCallback) {

        EspressoIdlingResource.increment()
        val client = ApiConfig.instance.getMovies(api_key, language)
        client.await().results.let { listMovie ->
            callback.onMoviesReceived(listMovie)
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getDetailMovie(movie_id : Int, callback: LoadMovieDetailCallback) {
        EspressoIdlingResource.increment()
        val client = ApiConfig.instance.getMovieDetail(movie_id, api_key)
        client.await().let { movieDetail ->
            val detail = MovieDetail(
                movieDetail.description,
                movieDetail.title,
                movieDetail.genre,
                movieDetail.poster,
                movieDetail.year,
                movieDetail.movieId
            )
            callback.onMovieDetailReceived(detail)
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getListTvShow(callback: LoadTvShowCallback) {
        EspressoIdlingResource.increment()
        val client = ApiConfig.instance.getTvShows(api_key, language)
        client.await().results.let { listTvShow ->
            callback.onTvShowsReceived(listTvShow)
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getDetailTvShow(tv_id : Int, callback: LoadTvShowDetailCallback) {
        EspressoIdlingResource.increment()
        val client = ApiConfig.instance.getTvDetail(tv_id, api_key)
        client.await().let { tvDetail ->
            val detail = TvDetail(
                tvDetail.year,
                tvDetail.eps,
                tvDetail.description,
                tvDetail.genre,
                tvDetail.poster,
                tvDetail.title,
                tvDetail.tvId
            )
            callback.onTvShowDetailReceived(detail)
            EspressoIdlingResource.decrement()
        }
    }


    interface LoadMoviesCallback {
        fun onMoviesReceived(movieResponse: List<ResultsItem>)
    }

    interface LoadTvShowCallback {
        fun onTvShowsReceived(tvResponse: List<TvResultsItem>)
    }

    interface LoadMovieDetailCallback {
        fun onMovieDetailReceived(movieDetailResponse: MovieDetail)
    }

    interface LoadTvShowDetailCallback {
        fun onTvShowDetailReceived(tvDetailResponse: TvDetail)
    }
}