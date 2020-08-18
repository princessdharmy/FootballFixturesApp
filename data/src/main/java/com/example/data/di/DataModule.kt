package com.example.data.di

import androidx.work.Configuration
import com.example.data.local.datasource.LocalDataSource
import com.example.data.local.datasource.LocalDataSourceImpl
import com.example.data.local.room.CompetitionsDao
import com.example.data.remote.api.ApiService
import com.example.data.remote.datasource.RemoteDataSource
import com.example.data.remote.datasource.RemoteDataSourceImpl
import com.example.data.repository.CompetitionsRepositoryImpl
import com.example.data.workmanager.FootballWorkerFactory
import com.example.domain.repository.CompetitionsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class DataModule {

    /// Provide Remote Data Sources ///

    @Provides
    fun provideRemoteDataSource(apiService: ApiService): RemoteDataSource {
        return RemoteDataSourceImpl(apiService)
    }

    /// Provide Local Data Sources ///

    @Provides
    fun provideLocalDataSource(competitionsDao: CompetitionsDao): LocalDataSource {
        return LocalDataSourceImpl(competitionsDao)
    }

    /// Provide repositories ///

    @Singleton
    @Provides
    fun provideCompetitionsRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource
    ): CompetitionsRepository {
        return CompetitionsRepositoryImpl(remoteDataSource, localDataSource)
    }

    /// Provide WorkManager ///

    @Singleton
    @Provides
    fun provideWorkManagerConfiguration(
        footballWorkerFactory: FootballWorkerFactory
    ): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(footballWorkerFactory)
            .build()
    }

}