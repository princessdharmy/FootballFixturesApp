package com.example.domain.repository

import com.example.common.utils.network.NetworkResult
import com.example.domain.entities.DomainEntities.*

interface CompetitionsRepository {

    suspend fun getTodayMatches(date: String): NetworkResult<DomainMatchResponse>

    suspend fun getAllCompetitionsFromDb(): List<DomainCompetitions>

    suspend fun getAllCompetitions()

    suspend fun getStandings(id: Long): NetworkResult<DomainStandingResponse>

    suspend fun getSingleMatch(id: Long, date: String): NetworkResult<DomainMatchResponse>

    suspend fun getTeam(id: Long): NetworkResult<DomainTeamResponse>

    suspend fun getPlayers(id: Long): NetworkResult<DomainPlayerResponse>

}