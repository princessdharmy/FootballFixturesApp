package com.app.footballfixtures.core.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.app.footballfixtures.core.data.models.Competitions
import com.app.footballfixtures.core.data.models.Season
import com.app.footballfixtures.utils.Converters


@Database(entities = arrayOf(Competitions::class, Season::class), version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun competitionsDao(): CompetitionsDao
}
