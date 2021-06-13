package com.jetpack.api

import com.jetpack.data.source.remote.response.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("movie/popular")
    fun getMovies(@Query("api_key") apiKey: String,
                  @Query("language") language: String)
            : Call<MovieResponse>

    @GET("genre/movie/list")
    fun getGenres(@Query("api_key") api_key: String,
                  @Query("language") language: String)
            : Call<GenreResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetail(@Path("movie_id") movie_id: Int,
                       @Query("api_key") api_key : String)
            : Call<MovieDetail>

    @GET("tv/popular")
    fun getTvShows(@Query("api_key") api_key: String,
                   @Query("language") language: String)
            : Call<TvShowResponse>

    @GET("tv/{tv_id}")
    fun getTvDetail(@Path("tv_id") tv_id: Int,
                    @Query("api_key") api_key: String)
            : Call<TvDetail>

    @GET("genre/tv/list")
    fun getTvGenres(@Query("api_key") api_key: String,
                    @Query("language") language: String)
            : Call<GenreResponse>
}