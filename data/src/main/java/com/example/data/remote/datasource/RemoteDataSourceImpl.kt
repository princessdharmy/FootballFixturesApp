package com.example.data.remote.datasource

import com.example.common.utils.Result
import com.example.data.remote.api.ApiService
import com.example.data.utils.safeApiCall
import com.example.domain.entities.DomainEntities.*

class RemoteDataSourceImpl (private val apiService: ApiService): RemoteDataSource {

    override suspend fun getAllMatches(date: String): Result<DomainMatchResponse> {
        return safeApiCall {apiService.getAllMatches(date, date)}
    }

    override suspend fun getAllCompetitions(): Result<DomainCompetitionResponse> {
        return safeApiCall {apiService.getAllCompetitions("TIER_ONE")}
    }

    override suspend fun getStandings(id: Long): Result<DomainStandingResponse> {
        return safeApiCall {apiService.getTablesByCompetition(id, "TOTAL")}
    }

    override suspend fun getSingleMatch(id: Long, date: String): Result<DomainMatchResponse> {
        return safeApiCall {apiService.getMatchesByCompetition(id, date, date)}
    }

    override suspend fun getTeam(id: Long): Result<DomainTeamResponse> {
        return safeApiCall {apiService.getTeamsByCompetition(id)}
    }

    override suspend fun getPlayers(id: Long): Result<DomainPlayerResponse> {
        return safeApiCall {apiService.getTeamById(id)}
    }
}