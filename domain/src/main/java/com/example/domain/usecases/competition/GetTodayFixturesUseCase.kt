package com.example.domain.usecases.competition

import com.example.common.utils.Result
import com.example.domain.entities.DomainEntities.*
import com.example.domain.repository.CompetitionsRepository
import javax.inject.Inject

class GetTodayFixturesUseCase @Inject constructor(
    private val competitionsRepository: CompetitionsRepository) {

    suspend operator fun invoke(input: String?): Result<DomainMatchResponse> {
        return competitionsRepository.getTodayMatches(input.toString())
    }

}