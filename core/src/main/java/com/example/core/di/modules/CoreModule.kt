package com.example.core.di.modules

import android.app.Application
import android.content.Context
import com.example.core.MainApplication
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CoreModule {

    @Binds
    @Singleton
    fun bindApplication(application: MainApplication): Application {
        return application
    }

    @Provides
    fun provideContext(application: Application): Context {
        return application
    }
}