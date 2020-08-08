package com.example.core.di.components

import android.content.Context
import com.example.core.di.modules.CoreModule
import com.example.core.di.modules.DatabaseModule
import com.example.data.di.DataModule
import com.example.domain.repository.CompetitionsRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [CoreModule::class, DataModule::class, DatabaseModule::class])
interface CoreComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): CoreComponent
    }

    val competitionsRepository: CompetitionsRepository

}