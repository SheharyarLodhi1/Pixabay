package com.sheharyar.pixabay.di

import android.content.Context
import com.example.rickandmorty.data.local.AppDatabase
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sheharyar.pixabay.data.local.HitsListDao
import com.sheharyar.pixabay.data.remote.PixabayRemoteDataSource
import com.sheharyar.pixabay.data.remote.PixabayService
import com.sheharyar.pixabay.data.repository.PixabayRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("https://pixabay.com/api/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun ProvidePixabayService(retrofit: Retrofit) : PixabayService = retrofit.create(PixabayService::class.java)

    @Singleton
    @Provides
    fun providePixabayRemoteDataSource(pixabayService: PixabayService) = PixabayRemoteDataSource(pixabayService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun providePixabayDao(db: AppDatabase) = db.hitsListDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: PixabayRemoteDataSource,
                          localDataSource: HitsListDao) =
        PixabayRepository(remoteDataSource, localDataSource)
}