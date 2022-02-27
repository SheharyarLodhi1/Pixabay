package com.sheharyar.pixabay.data.remote

import com.sheharyar.pixabay.data.entities.HitsList
import com.sheharyar.pixabay.data.entities.PixabayList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PixabayService {
    @GET("?key=25835022-0734bc09be585e14c14bca983&q=yellow+flowers&image_type=photo")
    suspend fun getAllDetails() : Response<PixabayList>

   /* @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): Response<Character>*/
}