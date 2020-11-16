package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.local.room.CompetitionsDao
import com.example.data.local.room.FootballDatabase
import com.example.data.local.room.FootballDatabase.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object DatabaseModule {

    /*
     * The method returns the Database object
     **/
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): FootballDatabase = Room.databaseBuilder(
        context, FootballDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration() // allows database to be cleared after upgrading version
            .build()

    /*
    * We need the CompetitionsDao module.
    * For this, We need the FootballDatabase object
    * So we will define the providers for this here in this module.
    * */
    @Singleton
    @Provides
    fun provideCompetitionDao(footballDatabase: FootballDatabase):
            CompetitionsDao = footballDatabase.competitionsDao()
}