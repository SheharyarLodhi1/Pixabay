package com.example.rickandmorty.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sheharyar.pixabay.data.entities.HitsList
import com.sheharyar.pixabay.data.local.HitsListDao

@Database(entities = [HitsList::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun hitsListDao(): HitsListDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "hits")
                .fallbackToDestructiveMigration()
                .build()
    }

}