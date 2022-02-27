package com.sheharyar.pixabay.data.repository

import com.sheharyar.pixabay.data.local.HitsListDao
import com.sheharyar.pixabay.data.remote.PixabayRemoteDataSource
import com.sheharyar.pixabay.utils.performDbOperation
import com.sheharyar.pixabay.utils.performGetOperation
import javax.inject.Inject

class PixabayRepository @Inject constructor(
    private val remoteDataSource: PixabayRemoteDataSource,
    private val localDataSource: HitsListDao
) {
    fun getPictures() = performGetOperation(
        databaseQuery = { localDataSource.getAllHits() },
        networkCall = { remoteDataSource.getPictureDetails() },
        saveCallResult = { localDataSource.insertAll(it.hits) }
    )

    fun getImageDetails() = performDbOperation(
        databaseQuery = { localDataSource.getAllHits() }
    )
}