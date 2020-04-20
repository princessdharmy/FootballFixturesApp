package com.example.domain.repository

import com.example.domain.entities.DomainEntities
import io.reactivex.Observable

interface CompetitionsRepository {

    fun getTodayMatches(): Observable<List<DomainEntities.MatchResponse>>

    fun getCompetitions(): Observable<List<DomainEntities.CompetitionResponse>>
}