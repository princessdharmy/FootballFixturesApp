package com.example.data.remote.datasource

import com.example.common.utils.network.NetworkResult
import com.example.domain.entities.DomainEntities.*

interface RemoteDataSource {

    suspend fun getAllMatches(date: String): NetworkResult<DomainMatchResponse>

    suspend fun getAllCompetitions(): NetworkResult<DomainCompetitionResponse>

    suspend fun getStandings(id: Long): NetworkResult<DomainStandingResponse>

    suspend fun getSingleMatch(id: Long, date: String): NetworkResult<DomainMatchResponse>

    suspend fun getTeam(id: Long): NetworkResult<DomainTeamResponse>

    suspend fun getPlayers(id: Long): NetworkResult<DomainPlayerResponse>

}