package com.example.data.di

import com.example.data.local.datasource.LocalDataSource
import com.example.data.remote.api.ApiService
import com.example.data.remote.datasource.RemoteDataSource
import com.example.data.remote.datasource.RemoteDataSourceImpl
import com.example.data.repository.CompetitionsRepositoryImpl
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

    /// Provide repositories ///

    @Singleton
    @Provides
    fun provideCompetitionsRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource
    ): CompetitionsRepository {
        return CompetitionsRepositoryImpl(remoteDataSource, localDataSource)
    }

//    @Provides
//    @Singleton
//    fun provideAppDatabase(context: Context): AppDatabase = Room.databaseBuilder(
//        context, AppDatabase::class.java, "competitions_db"
//    ).fallbackToDestructiveMigration().build()
//
//    @Provides
//    @Singleton
//    fun provideCompetitionDao(appDatabase: AppDatabase):
//            CompetitionsDao = appDatabase.competitionsDao()

}