package com.jetpack.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class MovieResponse(
    @field:SerializedName("results")
    val results: List<ResultsItem>
)

@Parcelize
data class ResultsItem(
    @field:SerializedName("overview")
    val description: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("genre_ids")
    val genre: List<Int>,

    @field:SerializedName("poster_path")
    val poster: String,

    @field:SerializedName("release_date")
    val year: String,

    @field:SerializedName("id")
    val movieId: Int
) : Parcelable

data class MovieDetail(
    @field:SerializedName("overview")
    val description: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("genres")
    val genre: List<GenresItem>,

    @field:SerializedName("poster_path")
    val poster: String,

    @field:SerializedName("release_date")
    val year: String,

    @field:SerializedName("id")
    val movieId: Int
)
