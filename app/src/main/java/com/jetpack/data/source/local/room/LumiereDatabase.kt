package com.jetpack.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jetpack.data.local.entity.MovieEntity
import com.jetpack.data.local.entity.TvShowEntity

@Database(
    entities = [MovieEntity::class, TvShowEntity::class],
    version = 1,
    exportSchema = false
)
abstract class LumiereDatabase : RoomDatabase() {
    abstract fun lumiereDao(): LumiereDao

    companion object {
        @Volatile
        private var INSTANCE: LumiereDatabase? = null

        fun getInstance(context: Context): LumiereDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: Room.databaseBuilder(context.applicationContext, LumiereDatabase::class.java, "Lumiere.db").build()
            }
    }
}