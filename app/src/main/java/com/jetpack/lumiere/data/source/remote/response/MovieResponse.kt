package com.jetpack.lumiere.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
	@field:SerializedName("results")
	val results: List<ResultsItem>
)

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
)

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
