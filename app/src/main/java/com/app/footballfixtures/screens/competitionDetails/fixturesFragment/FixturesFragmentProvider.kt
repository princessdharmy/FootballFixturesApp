package com.app.footballfixtures.screens.competitionDetails.fixturesFragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FixturesFragmentProvider {

    @ContributesAndroidInjector(modules = arrayOf(FixturesModule::class))
    internal abstract fun contributeFixturesProvider(): FixturesFragment
}