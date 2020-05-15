package com.example.domain.usecases.competition

import com.example.domain.entities.DomainEntities
import com.example.domain.qualifiers.Background
import com.example.domain.qualifiers.Foreground
import com.example.domain.repository.CompetitionsRepository
import com.example.domain.usecases.base.SingleUseCase
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

class GetTodayFixturesUseCase @Inject constructor(
    private val competitionsRepository: CompetitionsRepository,
    @Background backgroundScheduler: Scheduler,
    @Foreground foregroundScheduler: Scheduler
): SingleUseCase<DomainEntities.MatchResponse, String>(backgroundScheduler, foregroundScheduler) {

    override fun build(input: String?): Single<DomainEntities.MatchResponse> {
        return competitionsRepository.getTodayMatches(input.toString())
    }
}