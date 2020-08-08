package com.example.competitiondetails.di

import com.example.common.scopes.Fragment
import com.example.competitiondetails.bottomSheet.BottomSheetFragment
import com.example.competitiondetails.fixturesFragment.FixturesFragment
import com.example.competitiondetails.tableFragment.TableFragment
import com.example.competitiondetails.teamFragment.TeamFragment
import com.example.core.di.CoreComponent
import dagger.Component

@Component(dependencies = [CoreComponent::class],
    modules = [CompetitionDetailsViewModelModule::class]
)
@Fragment
interface CompetitionDetailsComponent {

    @Component.Factory
    interface Factory {
        fun create(component: CoreComponent): CompetitionDetailsComponent
    }


    fun inject(fixturesFragment: FixturesFragment)
    fun inject(tableFragment: TableFragment)
    fun inject(teamFragment: TeamFragment)
    fun inject(bottomSheetFragment: BottomSheetFragment)
}