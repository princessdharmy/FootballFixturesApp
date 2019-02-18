package com.app.footballfixtures.screens.competitionDetails.fixturesFragment

import android.arch.lifecycle.ViewModelProvider
import com.app.footballfixtures.core.data.Repository
import com.app.footballfixtures.utils.ViewModelProviderFactory
import dagger.Module
import dagger.Provides

@Module
class FixturesModule {

    @Provides
    internal fun provideViewModel(repository: Repository): FixturesViewModel {
        return FixturesViewModel(repository)
    }

    @Provides
    internal fun provideViewModelFactory(viewModel: FixturesViewModel): ViewModelProvider.Factory{
        return ViewModelProviderFactory(viewModel)
    }
}