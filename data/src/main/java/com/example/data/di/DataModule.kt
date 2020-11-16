package com.example.data.di

import com.example.data.coroutines.DefaultDispatcherProvider
import com.example.data.coroutines.DispatcherProvider
import com.example.data.local.datasource.LocalDataSource
import com.example.data.local.datasource.LocalDataSourceImpl
import com.example.data.local.room.CompetitionsDao
import com.example.data.remote.api.ApiService
import com.example.data.remote.datasource.RemoteDataSource
import com.example.data.remote.datasource.RemoteDataSourceImpl
import com.example.data.repository.CompetitionsRepositoryImpl
import com.example.domain.repository.CompetitionsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object DataModule {

    /// Provide DispatcherProvider ///

    @Provides
    @Singleton
    internal fun provideDispatcherProvider(): DispatcherProvider = DefaultDispatcherProvider()

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
        dispatcherProvider: DispatcherProvider,
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource
    ): CompetitionsRepository {
        return CompetitionsRepositoryImpl(dispatcherProvider, remoteDataSource, localDataSource)
    }

}