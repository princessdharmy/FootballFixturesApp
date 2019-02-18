package com.app.footballfixtures.screens.competition.today

import android.arch.lifecycle.ViewModelProvider
import com.app.footballfixtures.core.data.Repository
import com.app.footballfixtures.utils.ViewModelProviderFactory
import dagger.Module
import dagger.Provides

@Module
class TodayModule {

    @Provides
    internal fun provideViewModel(repository: Repository): TodayFixturesViewModel {
        return TodayFixturesViewModel(repository)
    }

    @Provides
    internal fun provideViewModelFactory(viewModel: TodayFixturesViewModel): ViewModelProvider.Factory{
        return ViewModelProviderFactory(viewModel)
    }
}