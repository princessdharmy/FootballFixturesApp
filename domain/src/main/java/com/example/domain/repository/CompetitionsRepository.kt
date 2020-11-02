package com.example.domain.repository

import com.example.common.utils.network.NetworkStatus
import com.example.domain.entities.DomainEntities.*

interface CompetitionsRepository {

    suspend fun getTodayMatches(date: String): NetworkStatus<DomainMatchResponse>

    suspend fun getAllCompetitionsFromDb(): List<DomainCompetitions>

    suspend fun getAllCompetitions()

    suspend fun getStandings(id: Long): NetworkStatus<DomainStandingResponse>

    suspend fun getSingleMatch(id: Long, date: String): NetworkStatus<DomainMatchResponse>

    suspend fun getTeam(id: Long): NetworkStatus<DomainTeamResponse>

    suspend fun getPlayers(id: Long): NetworkStatus<DomainPlayerResponse>

}