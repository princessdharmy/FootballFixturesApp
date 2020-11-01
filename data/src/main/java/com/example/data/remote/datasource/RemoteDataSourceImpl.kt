package com.example.data.remote.datasource

import com.example.common.utils.network.NetworkResult
import com.example.data.remote.api.ApiService
import com.example.data.utils.safeApiCall
import com.example.domain.entities.DomainEntities.*

class RemoteDataSourceImpl (private val apiService: ApiService): RemoteDataSource {

    override suspend fun getAllMatches(date: String): NetworkResult<DomainMatchResponse> {
        return safeApiCall {apiService.getAllMatches(date, date)}
    }

    override suspend fun getAllCompetitions(): NetworkResult<DomainCompetitionResponse> {
        return safeApiCall {apiService.getAllCompetitions("TIER_ONE")}
    }

    override suspend fun getStandings(id: Long): NetworkResult<DomainStandingResponse> {
        return safeApiCall {apiService.getTablesByCompetition(id, "TOTAL")}
    }

    override suspend fun getSingleMatch(id: Long, date: String): NetworkResult<DomainMatchResponse> {
        return safeApiCall {apiService.getMatchesByCompetition(id, date, date)}
    }

    override suspend fun getTeam(id: Long): NetworkResult<DomainTeamResponse> {
        return safeApiCall {apiService.getTeamsByCompetition(id)}
    }

    override suspend fun getPlayers(id: Long): NetworkResult<DomainPlayerResponse> {
        return safeApiCall {apiService.getTeamById(id)}
    }
}