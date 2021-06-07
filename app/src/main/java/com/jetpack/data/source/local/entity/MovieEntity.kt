package com.jetpack.data.source.local.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieEntity(
    var movieId: String,
    var title: String,
    var description: String,
    var year: String,
    var genre: String,
    var poster: String
) : Parcelable