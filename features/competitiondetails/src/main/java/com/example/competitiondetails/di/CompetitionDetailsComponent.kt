package com.example.competitiondetails.di

import com.example.common.scopes.Fragment
import com.example.competitiondetails.ui.bottomSheet.BottomSheetFragment
import com.example.competitiondetails.ui.fixturesFragment.FixturesFragment
import com.example.competitiondetails.ui.tableFragment.TableFragment
import com.example.competitiondetails.ui.teamFragment.TeamFragment
import com.example.core.di.components.CoreComponent
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