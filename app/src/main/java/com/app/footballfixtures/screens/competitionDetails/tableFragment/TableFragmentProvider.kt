package com.app.footballfixtures.screens.competitionDetails.tableFragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class TableFragmentProvider {

    @ContributesAndroidInjector(modules = arrayOf(TableModule::class))
    internal abstract fun contributeTableProvider(): TableFragment
}