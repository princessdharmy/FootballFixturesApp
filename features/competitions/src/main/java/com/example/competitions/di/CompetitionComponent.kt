package com.example.competitions.di

import com.example.common.scopes.Fragment
import com.example.competitions.competitions.CompetitionsFragment
import com.example.competitions.today.TodayFixturesFragment
import com.example.core.di.CoreComponent
import dagger.Component

@Component(
    dependencies = [
        CoreComponent::class
    ],
    modules = [
        CompetitionsModule::class
    ]
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