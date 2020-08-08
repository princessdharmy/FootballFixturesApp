package com.example.domain.usecases.competition

import com.example.common.utils.Result
import com.example.domain.entities.DomainEntities.*
import com.example.domain.repository.CompetitionsRepository
import javax.inject.Inject

class GetCompetitionsUseCase @Inject constructor(
    private val competitionsRepository: CompetitionsRepository
) {

    suspend operator fun invoke(): Result<DomainCompetitionResponse> {
        return competitionsRepository.getAllCompetitions()
    }

}