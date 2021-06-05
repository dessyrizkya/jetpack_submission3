package com.jetpack.lumiere.di

import com.jetpack.lumiere.data.source.LumiereRepository
import com.jetpack.lumiere.data.source.remote.RemoteDataSource
import okhttp3.OkHttpClient

object Injection {
    fun provideLumiereRepository(): LumiereRepository {
        val remoteDataSource = RemoteDataSource.getInstance()
        return LumiereRepository.getInstance(remoteDataSource)
    }
}