package com.example.data.workmanager

import androidx.work.DelegatingWorkerFactory
import com.example.domain.usecases.workmanager.RefreshCompetitionsUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FootballWorkerFactory @Inject constructor(
    refreshCompetitionsUseCase: RefreshCompetitionsUseCase,
    workNetworkService: WorkNetworkService
) : DelegatingWorkerFactory() {

    init {
        addFactory(CompetitionDataWorkerFactory(refreshCompetitionsUseCase, workNetworkService))
    }
}