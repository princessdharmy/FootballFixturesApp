package com.example.data.repository

import com.example.data.local.datasource.LocalDataSource
import com.example.data.local.mapper.map
import com.example.data.remote.datasource.RemoteDataSource
import com.example.domain.entities.DomainEntities
import com.example.domain.repository.CompetitionsRepository
import io.reactivex.Single

class CompetitionsRepositoryImpl (private val remoteDataSource: RemoteDataSource,
private val localDataSource: LocalDataSource): CompetitionsRepository {

    override fun getTodayMatches(date: String): Single<DomainEntities.MatchResponse> {
        return remoteDataSource.getAllMatches(date).map { it.map() }
    }

    override fun getAllCompetitions(): Single<DomainEntities.CompetitionResponse> {
        return remoteDataSource.getAllCompetitions().map { it.map() }
    }

    override fun getStandings(id: Long): Single<DomainEntities.StandingResponse> {
        return remoteDataSource.getStandings(id).map { it.map() }
    }

    override fun getSingleMatch(id: Long, date: String): Single<DomainEntities.MatchResponse> {
        return remoteDataSource.getSingleMatch(id, date).map { it.map() }
    }

    override fun getTeam(id: Long): Single<DomainEntities.TeamResponse> {
        return remoteDataSource.getTeam(id).map { it.map() }
    }

    override fun getPlayers(id: Long): Single<DomainEntities.PlayerResponse> {
        return remoteDataSource.getPlayers(id).map { it.map() }
    }
}