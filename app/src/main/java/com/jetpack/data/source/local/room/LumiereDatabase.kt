package com.jetpack.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [FavoriteMovie::class, FavoriteTvShow::class], version = 1)
abstract class LumiereDatabase : RoomDatabase() {
    abstract fun lumiereDao(): LumiereDao
}