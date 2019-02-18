package com.app.footballfixtures.screens.competition.today

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class TodayFragmentProvider {

    @ContributesAndroidInjector(modules = arrayOf(TodayModule::class))
    internal abstract fun contributeTodayProvider(): TodayFixturesFragment
}