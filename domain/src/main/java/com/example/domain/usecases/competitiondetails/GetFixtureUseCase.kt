package com.example.domain.usecases.competitiondetails

import com.example.domain.entities.DomainEntities
import com.example.domain.qualifiers.Background
import com.example.domain.qualifiers.Foreground
import com.example.domain.repository.CompetitionsRepository
import com.example.domain.usecases.base.SingleUseCase
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

class GetFixtureUseCase @Inject constructor (
    private val competitionsRepository: CompetitionsRepository,
    @Background backgroundScheduler: Scheduler,
    @Foreground foregroundScheduler: Scheduler
) : SingleUseCase<DomainEntities.MatchResponse, GetFixtureUseCase.GetParameters>(
    backgroundScheduler, foregroundScheduler){

    override fun build(input: GetParameters?): Single<DomainEntities.MatchResponse> {
        return competitionsRepository.getSingleMatch(input?.value1!!, input.value2)
    }

    data class GetParameters(val value1: Long, val value2: String)

    companion object {
        @JvmStatic
        fun make (value1: Long, value2: String) : GetParameters =
            GetParameters(
                value1,
                value2
            )

    }
}