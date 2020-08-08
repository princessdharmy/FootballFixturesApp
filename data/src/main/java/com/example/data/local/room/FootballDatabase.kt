package com.example.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.domain.entities.DomainEntities.*


@Database(entities = [DomainCompetitions::class, DomainSeason::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class FootballDatabase: RoomDatabase() {
    abstract fun competitionsDao(): CompetitionsDao

    companion object {
        const val DATABASE_NAME = "competitions_db"
    }
}
