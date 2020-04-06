package com.example.domain.di.modules

import com.example.domain.qualifiers.Background
import com.example.domain.qualifiers.Foreground
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Module
class DomainModule {

    @Provides
    @Singleton
    @Background
    fun providesExecutionScheduler(): Scheduler = Schedulers.io()

    @Provides
    @Singleton
    @Foreground
    fun providesPostExecutionScheduler(): Scheduler = AndroidSchedulers.mainThread()
}