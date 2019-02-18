package com.app.footballfixtures.screens.competitionDetails.teamFragment

import android.arch.lifecycle.ViewModelProvider
import com.app.footballfixtures.core.data.Repository
import com.app.footballfixtures.utils.ViewModelProviderFactory
import dagger.Module
import dagger.Provides

@Module
class TeamModule {

    @Provides
    internal fun provideViewModel(repository: Repository): TeamViewModel {
        return TeamViewModel(repository)
    }

    @Provides
    internal fun provideViewModelFactory(viewModel: TeamViewModel): ViewModelProvider.Factory{
        return ViewModelProviderFactory(viewModel)
    }
}