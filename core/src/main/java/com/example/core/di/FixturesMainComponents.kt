package com.example.core.di

import android.content.Context
import com.example.data.di.DataModule
import com.example.data.di.NetworkModule
import com.example.domain.di.modules.DomainModule
import com.example.presentation.di.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [CoreModule::class, DataModule::class, NetworkModule::class,
    DomainModule::class
//    , ViewModelModule::class
])
interface CoreComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): CoreComponent
    }

}