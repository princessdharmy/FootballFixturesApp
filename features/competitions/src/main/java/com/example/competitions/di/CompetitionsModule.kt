package com.example.competitions.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.common.scopes.Fragment
import com.example.common.scopes.ViewModelKey
import com.example.presentation.factory.ViewModelFactory
import com.example.presentation.viewmodels.CompetitionsViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
interface CompetitionsModule {

    @Binds
    @Fragment
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CompetitionsViewModel::class)
    fun bindCompetitionsViewModel(viewModel: CompetitionsViewModel): ViewModel

}