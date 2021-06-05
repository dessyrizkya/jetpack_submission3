package com.jetpack.lumiere.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FavoriteMovie::class,FavoriteTvShow::class], version = 1)
abstract class LumiereDatabase : RoomDatabase() {

    abstract fun lumiereDao(): LumiereDao

    companion object {
        @Volatile
        private var INSTANCE: LumiereDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): LumiereDatabase {
            if (INSTANCE == null) {
                synchronized(LumiereDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                    LumiereDatabase::class.java,
                    "lumiere_database")
                        .build()
                }
            }
            return INSTANCE as LumiereDatabase
        }
    }
}