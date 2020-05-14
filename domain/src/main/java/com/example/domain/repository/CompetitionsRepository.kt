package com.example.domain.repository

import com.example.domain.entities.DomainEntities
import io.reactivex.Single

interface CompetitionsRepository {

    fun getTodayMatches(date: String): Single<DomainEntities.MatchResponse>

    fun getAllCompetitions(): Single<DomainEntities.CompetitionResponse>

    fun getStandings(id: Long): Single<DomainEntities.StandingResponse>

    fun getSingleMatch(id: Long, date: String): Single<DomainEntities.MatchResponse>

    fun getTeam(id: Long): Single<DomainEntities.TeamResponse>

    fun getPlayers(id: Long): Single<DomainEntities.PlayerResponse>
}