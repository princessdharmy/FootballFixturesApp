package com.example.domain.usecases.workmanager

import com.example.common.utils.Result
import com.example.domain.entities.DomainEntities.*
import com.example.domain.repository.CompetitionsRepository
import javax.inject.Inject

class RefreshCompetitionsUseCase @Inject constructor(
    private val competitionsRepository: CompetitionsRepository
) {

    suspend operator fun invoke(): Result<DomainCompetitionResponse> {
        return competitionsRepository.getAllCompetitions()
    }

}