package com.example.data.remote.datasource

import com.example.data.local.mapper.map
import com.example.data.models.*
import com.example.data.remote.api.ApiService
import io.reactivex.Single

class ApiDataSourceImpl (private val apiService: ApiService): ApiDataSource {

    override fun getAllMatches(date: String): Single<DataMatchResponse> {
        return apiService.getAllMatches(date, date)
    }

    override fun getAllCompetitions(): Single<DataCompetitionResponse> {
        return apiService.getAllCompetitions("TIER_ONE")
    }

    override fun getStandings(id: Long): Single<DataStandingResponse> {
        return apiService.getTablesByCompetition(id, "TOTAL")
    }

    override fun getSingleMatch(id: Long, date: String): Single<DataMatchResponse> {
        return apiService.getMatchesByCompetition(id, date, date)
    }

    override fun getTeam(id: Long): Single<DataTeamResponse> {
        return apiService.getTeamsByCompetition(id)
    }

    override fun getPlayers(id: Long): Single<DataPlayerResponse> {
        return apiService.getTeamById(id)
    }
}