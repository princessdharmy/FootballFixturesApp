package com.example.data.remote.datasource

import com.example.common.utils.Result
import com.example.domain.entities.DomainEntities.*

interface RemoteDataSource {

    suspend fun getAllMatches(date: String): Result<DomainMatchResponse>

    suspend fun getAllCompetitions(): Result<DomainCompetitionResponse>

    suspend fun getStandings(id: Long): Result<DomainStandingResponse>

    suspend fun getSingleMatch(id: Long, date: String): Result<DomainMatchResponse>

    suspend fun getTeam(id: Long): Result<DomainTeamResponse>

    suspend fun getPlayers(id: Long): Result<DomainPlayerResponse>

}