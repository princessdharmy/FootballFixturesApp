package com.example.competitiondetails.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.common.scopes.Fragment
import com.example.common.scopes.ViewModelKey
import com.example.presentation.factory.ViewModelFactory
import com.example.presentation.viewmodels.CompetitionDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface CompetitionDetailsModule {

    @Binds
    @Fragment
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CompetitionDetailsViewModel::class)
    fun bindFixturesViewModel(fixturesViewModel: CompetitionDetailsViewModel): ViewModel

}
