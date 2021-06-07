package com.jetpack.Api

import com.jetpack.data.source.remote.response.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    companion object {
        const val base_url = "https://api.themoviedb.org/3/"
        const val api_key = "2c5873fb96074b9507457be681dc065d"
    }

    @GET("movie/popular?api_key=$api_key")
    suspend fun getMovies(@Query("page") position: Int): MovieResponse

    @GET("genre/movie/list?api_key=$api_key")
    fun getGenres(): Call<GenreResponse>

    @GET("movie/{movie_id}?api_key=$api_key")
    suspend fun getMovieDetail(@Path("movie_id") movie_id: Int): MovieDetail

    @GET("tv/popular?api_key=$api_key")
    suspend fun getTvShows(@Query("page") position: Int): TvShowResponse

    @GET("tv/{tv_id}?api_key=$api_key")
    suspend fun getTvDetail(@Path("tv_id") tv_id: Int): TvDetail

    @GET("genre/tv/list?api_key=$api_key")
    fun getTvGenres(): Call<GenreResponse>
}