package com.example.domain.usecases.competition

import com.example.common.utils.network.NetworkStatus
import com.example.domain.entities.DomainEntities.*
import com.example.domain.repository.CompetitionsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCompetitionsUseCase @Inject constructor(
    private val competitionsRepository: CompetitionsRepository
) {

    suspend operator fun invoke(): Flow<NetworkStatus<List<DomainCompetitions>>> {
        return competitionsRepository.getAllCompetitions()
    }

}