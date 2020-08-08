package com.example.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.local.room.CompetitionsDao
import com.example.data.local.room.Converters
import com.example.data.models.DataCompetitions
import com.example.data.models.DataSeason


@Database(entities = [DataCompetitions::class, DataSeason::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class FootballDatabase: RoomDatabase() {
    abstract fun competitionsDao(): CompetitionsDao

    companion object {
        const val DATABASE_NAME = "competitions_db"
    }
}
