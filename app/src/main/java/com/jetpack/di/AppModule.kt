package com.jetpack.di

import android.app.Application
import com.jetpack.api.Api
import com.jetpack.data.local.LocalDataSource
import com.jetpack.data.local.room.LumiereDao
import com.jetpack.data.local.room.LumiereDatabase
import com.jetpack.data.source.LumiereRepository
import com.jetpack.data.source.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val base_url = "https://api.themoviedb.org/3/"

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient = OkHttpClient.Builder().apply {
        connectTimeout(30, TimeUnit.SECONDS)
        readTimeout(30, TimeUnit.SECONDS)
        writeTimeout(30, TimeUnit.SECONDS)
    }.build()

    @Singleton
    @Provides
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder().apply {
        baseUrl(base_url)
        client(okHttpClient)
        addConverterFactory(GsonConverterFactory.create())
    }.build()

    @Provides
    fun provideApi(retrofit: Retrofit): Api = retrofit.create(Api::class.java)



    @Singleton
    @Provides
    fun provideDatabase(application: Application): LumiereDatabase =
        LumiereDatabase.getInstance(application)

    @Singleton
    @Provides
    fun provideDao(lumiereDatabase: LumiereDatabase): LumiereDao =
        lumiereDatabase.lumiereDao()

    @Singleton
    @Provides
    fun provideLocalDataSource(lumiereDao: LumiereDao): LocalDataSource =
        LocalDataSource(lumiereDao)

    @Singleton
    @Provides
    fun provideRemoteDataSource(api: Api): RemoteDataSource =
        RemoteDataSource(api)

    @Singleton
    @Provides
    fun provideCatalogRepository(
        api: Api,
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource
    ): LumiereRepository = LumiereRepository(api, remoteDataSource, localDataSource)

}
