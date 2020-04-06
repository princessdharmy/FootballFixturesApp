/*
package com.example.data

import android.arch.persistence.room.Room
import android.content.Context
import com.example.core.di.FixturesApplication
import com.app.footballfixtures.core.data.Repository
import com.app.footballfixtures.core.data.db.AppDatabase
import com.app.footballfixtures.core.data.db.CompetitionsDao
import com.app.footballfixtures.core.data.network.ApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FixturesMainModule {

    @Singleton
    @Provides
    internal fun provideContext(application: FixturesApplication): Context{
        return application
    }

    @Provides
    internal fun provideRepository(apiService: ApiService, dao: CompetitionsDao): Repository{
        return Repository(apiService, dao)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase = Room.databaseBuilder(
        context, AppDatabase::class.java, "competitions_db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideCompetitionDao(appDatabase: AppDatabase):
            CompetitionsDao = appDatabase.competitionsDao()


}*/
