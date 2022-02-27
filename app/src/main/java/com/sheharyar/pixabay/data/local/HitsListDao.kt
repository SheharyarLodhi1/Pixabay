package com.sheharyar.pixabay.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sheharyar.pixabay.data.entities.HitsList

@Dao
interface HitsListDao {
    @Query("SELECT * FROM hits")
    fun getAllHits() : LiveData<List<HitsList>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(hits: List<HitsList>)

   /* @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(hits: HitsList)*/
}