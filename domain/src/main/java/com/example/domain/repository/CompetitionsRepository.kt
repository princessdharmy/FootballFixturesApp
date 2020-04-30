package com.example.domain.repository

import com.example.domain.entities.DomainEntities
import io.reactivex.Single

interface CompetitionsRepository {

    fun getTodayMatches(date: String): Single<DomainEntities.MatchResponse>

    fun getAllCompetitions(): Single<DomainEntities.CompetitionResponse>
}