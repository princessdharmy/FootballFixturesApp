package com.example.domain.usecases

import com.example.domain.entities.DomainEntities
import com.example.domain.qualifiers.Background
import com.example.domain.qualifiers.Foreground
import com.example.domain.repository.CompetitionsRepository
import com.example.domain.usecases.base.ObservableUseCase
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject

class GetCompetitionsUseCase @Inject constructor(
    private val competitionsRepository: CompetitionsRepository,
    @Background backgroundScheduler: Scheduler,
    @Foreground foregroundScheduler: Scheduler
): ObservableUseCase<List<DomainEntities.CompetitionResponse>, Unit>(
    backgroundScheduler, foregroundScheduler){

    override fun build(input: Unit?): Observable<List<DomainEntities.CompetitionResponse>> {
        return competitionsRepository.getCompetitions()
    }
}