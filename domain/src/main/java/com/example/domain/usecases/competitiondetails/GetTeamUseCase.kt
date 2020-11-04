package com.example.domain.usecases.competitiondetails

import com.example.common.utils.network.NetworkStatus
import com.example.domain.entities.DomainEntities.*
import com.example.domain.repository.CompetitionsRepository
import javax.inject.Inject

class GetTeamUseCase @Inject constructor (
    private val competitionsRepository: CompetitionsRepository){

    suspend operator fun invoke(input: Long?): NetworkStatus<DomainTeamResponse> {
        return competitionsRepository.getTeam(input!!)
    }

}