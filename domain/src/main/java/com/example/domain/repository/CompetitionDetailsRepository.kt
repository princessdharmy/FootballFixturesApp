package com.example.domain.repository

import com.example.domain.entities.DomainEntities
import io.reactivex.Observable

interface CompetitionDetailsRepository {

    fun getStandings(): Observable<List<DomainEntities.StandingResponse>>

    fun getSingleMatch(): Observable<List<DomainEntities.Match>>

    fun getTeams(): Observable<List<DomainEntities.TeamResponse>>

    fun getPlayers(): Observable<List<DomainEntities.PlayerResponse>>
}