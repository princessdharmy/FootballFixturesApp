package com.app.footballfixtures.core.dagger.builders

import com.app.footballfixtures.screens.competition.HomeActivity
import com.app.footballfixtures.screens.competition.HomeActivityModule
import com.app.footballfixtures.screens.competition.competitions.CompetitionsFragmentProvider
import com.app.footballfixtures.screens.competition.today.TodayFragmentProvider
import com.app.footballfixtures.screens.competitionDetails.CompetitionDetailsActivity
import com.app.footballfixtures.screens.competitionDetails.CompetitionDetailsActivityModule
import com.app.footballfixtures.screens.competitionDetails.fixturesFragment.FixturesFragmentProvider
import com.app.footballfixtures.screens.competitionDetails.tableFragment.TableFragmentProvider
import com.app.footballfixtures.screens.competitionDetails.teamFragment.TeamFragmentProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = arrayOf(HomeActivityModule::class,
                                            TodayFragmentProvider::class,
                                            CompetitionsFragmentProvider::class))
    internal abstract fun contributeFixturesActivityModule(): HomeActivity

    @ContributesAndroidInjector(modules = arrayOf(CompetitionDetailsActivityModule::class,
                                            FixturesFragmentProvider::class,
                                            TableFragmentProvider::class,
                                            TeamFragmentProvider::class))
    internal abstract fun contributeMatchesActivityModule(): CompetitionDetailsActivity
}