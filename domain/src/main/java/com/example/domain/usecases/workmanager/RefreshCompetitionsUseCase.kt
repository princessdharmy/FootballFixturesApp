package com.example.domain.usecases.workmanager

import com.example.domain.repository.CompetitionsRepository
import javax.inject.Inject

class RefreshCompetitionsUseCase @Inject constructor(
    private val competitionsRepository: CompetitionsRepository
) {

    suspend operator fun invoke() {
        return competitionsRepository.refreshCacheWithRemoteCompetitionData()
    }

}