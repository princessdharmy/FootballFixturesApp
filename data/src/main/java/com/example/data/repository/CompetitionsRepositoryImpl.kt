package com.example.data.repository

import com.example.data.local.mapper.map
import com.example.data.remote.datasource.ApiDataSource
import com.example.domain.entities.DomainEntities
import com.example.domain.repository.CompetitionsRepository
import io.reactivex.Single

class CompetitionsRepositoryImpl (private val apiDataSource: ApiDataSource): CompetitionsRepository {

    override fun getTodayMatches(date: String): Single<DomainEntities.MatchResponse> {
        return apiDataSource.getAllMatches(date).map { it.map() }
    }

    override fun getAllCompetitions(): Single<DomainEntities.CompetitionResponse> {
        return apiDataSource.getAllCompetitions().map { it.map() }
    }

    override fun getStandings(id: Long): Single<DomainEntities.StandingResponse> {
        return apiDataSource.getStandings(id).map { it.map() }
    }

    override fun getSingleMatch(id: Long, date: String): Single<DomainEntities.MatchResponse> {
        return apiDataSource.getSingleMatch(id, date).map { it.map() }
    }

    override fun getTeam(id: Long): Single<DomainEntities.TeamResponse> {
        return apiDataSource.getTeam(id).map { it.map() }
    }

    override fun getPlayers(id: Long): Single<DomainEntities.PlayerResponse> {
        return apiDataSource.getPlayers(id).map { it.map() }
    }
}