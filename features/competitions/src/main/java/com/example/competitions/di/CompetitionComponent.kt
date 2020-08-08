package com.example.competitions.di

import com.example.common.scopes.Fragment
import com.example.competitions.ui.competitions.CompetitionsFragment
import com.example.competitions.ui.today.TodayFixturesFragment
import com.example.core.di.components.CoreComponent
import dagger.Component

@Component(dependencies = [CoreComponent::class],
    modules = [CompetitionsViewModelModule::class]
)
@Fragment
interface CompetitionComponent {

    @Component.Factory
    interface Factory {
        fun create(component: CoreComponent): CompetitionComponent
    }


    fun inject(competitionsFragment: CompetitionsFragment)
    fun inject(todayFixturesFragment: TodayFixturesFragment)
}