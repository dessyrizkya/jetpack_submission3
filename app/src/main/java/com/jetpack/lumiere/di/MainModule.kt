package com.jetpack.lumiere.di

import com.jetpack.lumiere.api.ApiConfig
import com.jetpack.lumiere.utils.LumiereApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import javax.inject.Singleton

@Module
@InstallIn(LumiereApplication::class)
object MainModule {

    @Provides
    @Singleton
    fun provideApi() = ApiConfig.instance
}