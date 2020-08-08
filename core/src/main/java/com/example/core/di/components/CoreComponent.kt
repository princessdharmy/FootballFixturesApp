package com.example.core.di.components

import android.content.Context
import com.example.core.di.modules.CoreModule
import com.example.data.di.DataModule
import com.example.domain.di.modules.DomainModule
import com.example.domain.qualifiers.Background
import com.example.domain.qualifiers.Foreground
import com.example.domain.repository.CompetitionsRepository
import dagger.BindsInstance
import dagger.Component
import io.reactivex.Scheduler
import javax.inject.Singleton


@Singleton
@Component(modules = [CoreModule::class, DataModule::class,
    DomainModule::class
])
interface CoreComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): CoreComponent
    }

    val competitionsRepository: CompetitionsRepository

    @Background
    fun backgroundSchedulers(): Scheduler

    @Foreground
    fun foregroundSchedulers(): Scheduler

}