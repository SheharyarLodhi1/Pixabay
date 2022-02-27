package com.sheharyar.pixabay.data.entities

data class PixabayList(
    val total: Int,
    val totalHits : Int,
    val hits : List<HitsList>
)