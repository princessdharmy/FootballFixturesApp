package com.example.domain.repository

import androidx.lifecycle.LiveData
import com.example.common.utils.Result
import com.example.domain.entities.DomainEntities.*

interface CompetitionsRepository {

    suspend fun getTodayMatches(date: String): Result<DomainMatchResponse>

    suspend fun getStandings(id: Long): Result<DomainStandingResponse>

    suspend fun getSingleMatch(id: Long, date: String): Result<DomainMatchResponse>

    suspend fun getTeam(id: Long): Result<DomainTeamResponse>

    suspend fun getPlayers(id: Long): Result<DomainPlayerResponse>

    suspend fun refreshCacheWithRemoteCompetitionData()

    fun getAllCompetitionsFromDb(): List<DomainCompetitions>
}