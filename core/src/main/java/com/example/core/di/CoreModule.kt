package com.example.core.di

import android.app.Application
import com.example.core.FixturesApplication
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class CoreModule {

    @Binds
    @Singleton
    abstract fun bindApplication(application: FixturesApplication): Application
}