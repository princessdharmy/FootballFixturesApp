package com.example.domain.usecases.competition

import com.example.common.utils.network.NetworkStatus
import com.example.domain.entities.DomainEntities.*
import com.example.domain.repository.CompetitionsRepository
import javax.inject.Inject

class GetTodayFixturesUseCase @Inject constructor(
    private val competitionsRepository: CompetitionsRepository) {

    suspend operator fun invoke(input: String?): NetworkStatus<DomainMatchResponse> {
        return competitionsRepository.getTodayMatches(input.toString())
    }

}