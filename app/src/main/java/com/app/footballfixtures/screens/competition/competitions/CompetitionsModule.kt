package com.app.footballfixtures.screens.competition.competitions

import android.arch.lifecycle.ViewModelProvider
import com.app.footballfixtures.core.data.Repository
import com.app.footballfixtures.utils.ViewModelProviderFactory
import dagger.Module
import dagger.Provides

@Module
class CompetitionsModule {

    @Provides
    internal fun provideViewModel(repository: Repository): CompetitionsViewModel{
        return CompetitionsViewModel(repository)
    }

    @Provides
    internal fun provideViewModelFactory(viewModel: CompetitionsViewModel): ViewModelProvider.Factory{
        return ViewModelProviderFactory(viewModel)
    }
}