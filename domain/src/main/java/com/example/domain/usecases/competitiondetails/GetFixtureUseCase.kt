package com.example.domain.usecases.competitiondetails

import com.example.common.utils.network.NetworkStatus
import com.example.domain.entities.DomainEntities.*
import com.example.domain.repository.CompetitionsRepository
import javax.inject.Inject

class GetFixtureUseCase @Inject constructor(
    private val competitionsRepository: CompetitionsRepository
) {

    suspend operator fun invoke(input: GetParameters?): NetworkStatus<DomainMatchResponse> {
        return competitionsRepository.getSingleMatch(input?.value1!!, input.value2)
    }

    data class GetParameters(val value1: Long, val value2: String)

    companion object {
        @JvmStatic
        fun make(value1: Long, value2: String): GetParameters =
            GetParameters(
                value1,
                value2
            )

    }
}