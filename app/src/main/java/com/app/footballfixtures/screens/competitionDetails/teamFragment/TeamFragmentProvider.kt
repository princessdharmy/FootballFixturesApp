package com.app.footballfixtures.screens.competitionDetails.teamFragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class TeamFragmentProvider {

    @ContributesAndroidInjector(modules = arrayOf(TeamModule::class))
    internal abstract fun contributeTeamProvider(): TeamFragment
}