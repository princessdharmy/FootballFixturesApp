package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.AppDatabase
import com.example.data.CompetitionsDao
import com.example.data.remote.api.ApiService
import com.example.data.remote.datasource.ApiDataSource
import com.example.data.remote.datasource.ApiDataSourceImpl
import com.example.data.repository.CompetitionsRepositoryImpl
import com.example.domain.repository.CompetitionsRepository
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

    @Provides
    fun provideApiDataSource(apiService: ApiService): ApiDataSource = ApiDataSourceImpl(apiService)

    @Singleton
    @Provides
    fun provideCompetitionsRepository(apiDataSource: ApiDataSource): CompetitionsRepository =
        CompetitionsRepositoryImpl(apiDataSource)

}