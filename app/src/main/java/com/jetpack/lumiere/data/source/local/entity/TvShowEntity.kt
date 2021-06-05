package com.jetpack.lumiere.data.source.local.entity

data class TvShowEntity(
    var tvshowId: String,
    var title: String,
    var description: String,
    var year: String,
    var genre: String,
    var poster: String
)

data class TvShowDetailEntity(
    var tvshowId: String,
    var title: String,
    var description: String,
    var year: String,
    var genre: String,
    var poster: String,
    var episode: String
)