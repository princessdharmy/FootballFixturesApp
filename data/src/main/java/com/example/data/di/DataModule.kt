package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.AppDatabase
import com.example.data.CompetitionsDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase = Room.databaseBuilder(
        context, AppDatabase::class.java, "competitions_db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideCompetitionDao(appDatabase: AppDatabase):
            CompetitionsDao = appDatabase.competitionsDao()

}