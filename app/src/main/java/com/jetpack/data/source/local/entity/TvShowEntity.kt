package com.jetpack.data.source.local.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class TvShowEntity(
    var tvshowId: String,
    var title: String,
    var description: String,
    var year: String,
    var genre: String,
    var poster: String
)

@Parcelize
data class TvShowDetailEntity(
    var tvshowId: String,
    var title: String,
    var description: String,
    var year: String,
    var genre: String,
    var poster: String,
    var episode: String
) : Parcelable