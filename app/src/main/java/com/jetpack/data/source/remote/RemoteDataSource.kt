package com.jetpack.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jetpack.api.Api
import com.jetpack.data.local.entity.TvShowEntity
import com.jetpack.data.source.remote.response.MovieDetail
import com.jetpack.data.source.remote.response.ResultsItem
import com.jetpack.data.source.remote.response.TvDetail
import com.jetpack.data.source.remote.response.TvResultsItem
import com.jetpack.utils.EspressoIdlingResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val api:Api) {

    companion object {
        const val apiKey = "2c5873fb96074b9507457be681dc065d"
        const val language = "en-US"
    }

    fun getListMovie() : LiveData<ApiResponse<List<ResultsItem>>> {
        EspressoIdlingResource.increment()
        val resultMovies = MutableLiveData<ApiResponse<List<ResultsItem>>>()
        CoroutineScope(IO).launch {
            try {
                val response = api.getMovies(apiKey, language).await()
                resultMovies.postValue(ApiResponse.success(response.results))
            }catch (e: IOException) {
                e.printStackTrace()
                resultMovies.postValue(
                    ApiResponse.error(
                        e.message.toString(),
                        mutableListOf()
                    )
                )
            }
        }

        EspressoIdlingResource.decrement()

        return resultMovies
    }

    fun getDetailMovie(movie_id : String) : LiveData<ApiResponse<MovieDetail>> {
        EspressoIdlingResource.increment()
        val resultMovie = MutableLiveData<ApiResponse<MovieDetail>>()
        CoroutineScope(IO).launch {
            try {
                val response = api.getMovieDetail(movie_id.toInt(), apiKey).await()
                resultMovie.postValue(ApiResponse.success(response))
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        EspressoIdlingResource.decrement()

        return resultMovie
    }


    fun getListTvShow() : LiveData<ApiResponse<List<TvResultsItem>>> {
        EspressoIdlingResource.increment()
        val resultTvs = MutableLiveData<ApiResponse<List<TvResultsItem>>>()
        CoroutineScope(IO).launch {
            try {
                val response = api.getTvShows(apiKey, language).await()
                resultTvs.postValue(ApiResponse.success(response.results))
            }catch (e: IOException) {
                e.printStackTrace()
                resultTvs.postValue(
                    ApiResponse.error(
                        e.message.toString(),
                        mutableListOf()
                    )
                )
            }
        }

        EspressoIdlingResource.decrement()

        return resultTvs
    }

    fun getDetailTvShow(tv_id : String) : LiveData<ApiResponse<TvDetail>> {
        EspressoIdlingResource.increment()
        val resultTv = MutableLiveData<ApiResponse<TvDetail>>()
        CoroutineScope(IO).launch {
            try {
                val response = api.getTvDetail(tv_id.toInt(), apiKey).await()
                resultTv.postValue(ApiResponse.success(response))
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        EspressoIdlingResource.decrement()

        return resultTv
    }

}