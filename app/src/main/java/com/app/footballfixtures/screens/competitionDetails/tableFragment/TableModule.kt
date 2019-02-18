package com.app.footballfixtures.screens.competitionDetails.tableFragment

import android.arch.lifecycle.ViewModelProvider
import com.app.footballfixtures.core.data.Repository
import com.app.footballfixtures.utils.ViewModelProviderFactory
import dagger.Module
import dagger.Provides

@Module
class TableModule {

    @Provides
    internal fun provideViewModel(repository: Repository): TableViewModel {
        return TableViewModel(repository)
    }

    @Provides
    internal fun provideViewModelFactory(viewModel: TableViewModel): ViewModelProvider.Factory{
        return ViewModelProviderFactory(viewModel)
    }
}