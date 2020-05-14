package com.example.data.remote.datasource

import com.example.data.models.*
import io.reactivex.Single

interface ApiDataSource {

    fun getAllMatches(date: String): Single<DataMatchResponse>

    fun getAllCompetitions(): Single<DataCompetitionResponse>

    fun getStandings(id: Long): Single<DataStandingResponse>

    fun getSingleMatch(id: Long, date: String): Single<DataMatchResponse>

    fun getTeam(id: Long): Single<DataTeamResponse>

    fun getPlayers(id: Long): Single<DataPlayerResponse>

}