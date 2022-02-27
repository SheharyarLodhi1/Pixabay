package com.sheharyar.pixabay.data.remote

import javax.inject.Inject

class PixabayRemoteDataSource @Inject constructor(
    private val pixabayService: PixabayService
): BaseDataSource() {

    suspend fun getPictureDetails() = getResult { pixabayService.getAllDetails() }
}