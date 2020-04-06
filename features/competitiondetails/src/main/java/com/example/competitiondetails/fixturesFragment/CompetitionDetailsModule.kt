package com.example.competitiondetails.fixturesFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.common.scopes.Fragment
import com.example.common.scopes.ViewModelKey
import com.example.presentation.factory.ViewModelFactory
import com.example.presentation.viewmodels.FixturesViewModel
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
    @ViewModelKey(FixturesViewModel::class)
    fun bindFixturesViewModel(fixturesViewModel: FixturesViewModel): ViewModel


//    @Provides
//    internal fun provideViewModel(repository: com.example.data.Repository): FixturesViewModel {
//        return FixturesViewModel(repository)
//    }
//
//    @Provides
//    internal fun provideViewModelFactory(viewModel: FixturesViewModel): ViewModelProvider.Factory{
//        return ViewModelProviderFactory(viewModel)
//    }
}
