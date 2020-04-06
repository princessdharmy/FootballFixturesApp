package com.example.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.models.DataCompetitions
import com.example.data.models.DataSeason


@Database(entities = [DataCompetitions::class, DataSeason::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun competitionsDao(): com.example.data.CompetitionsDao
}
