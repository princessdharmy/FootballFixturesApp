package com.example.data.remote.datasource

import com.example.common.utils.network.NetworkStatus
import com.example.domain.entities.DomainEntities.*

interface RemoteDataSource {

    suspend fun getAllMatches(date: String): NetworkStatus<DomainMatchResponse>

    suspend fun getAllCompetitions(): NetworkStatus<DomainCompetitionResponse>

    suspend fun getStandings(id: Long): NetworkStatus<DomainStandingResponse>

    suspend fun getSingleMatch(id: Long, date: String): NetworkStatus<DomainMatchResponse>

    suspend fun getTeam(id: Long): NetworkStatus<DomainTeamResponse>

    suspend fun getPlayers(id: Long): NetworkStatus<DomainPlayerResponse>

}