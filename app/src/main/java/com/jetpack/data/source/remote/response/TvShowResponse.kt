package com.jetpack.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class TvShowResponse(
    @field:SerializedName("results")
    val results: List<TvResultsItem>,
)

@Parcelize
data class TvResultsItem(

    @field:SerializedName("first_air_date")
    val year: String,

    @field:SerializedName("overview")
    val description: String,

    @field:SerializedName("genre_ids")
    val genreId: List<Int>,

    @field:SerializedName("poster_path")
    val poster: String,

    @field:SerializedName("original_name")
    val title: String,

    @field:SerializedName("id")
    val tvId: Int
) : Parcelable

data class TvDetail(

    @field:SerializedName("first_air_date")
    val year: String,

    @field:SerializedName("number_of_episodes")
    val eps: Int,

    @field:SerializedName("overview")
    val description: String,

    @field:SerializedName("genres")
    val genre: List<GenresItem>,

    @field:SerializedName("poster_path")
    val poster: String,

    @field:SerializedName("original_name")
    val title: String,

    @field:SerializedName("id")
    val tvId: Int,

    )