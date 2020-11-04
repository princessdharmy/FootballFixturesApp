package com.example.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import java.util.ArrayList

sealed class DomainEntities {

    /**
     * Data classes for Matches
     */
    data class DomainMatchResponse(
        var matches: List<DomainMatch> = ArrayList()
    ): DomainEntities()

    data class DomainMatch(
        var id: Long = 0L,
        var competition: DomainSubTeams? = null,
        var season: DomainSeason = DomainSeason(),
        var utcDate: String = "",
        var status: String = "",
        var matchday: Int = 0,
        var stage: String = "",
        var group: String = "",
        var lastUpdated: String = "",
        var score: DomainScore = DomainScore(),
        var homeTeam: DomainSubTeams = DomainSubTeams(),
        var awayTeam: DomainSubTeams = DomainSubTeams()
    ): DomainEntities()

    // @JsonClass(generateAdapter = true) is an annotation processor for Moshi’s Kotlin codegen support
    // It generates an adapter to be used when saving [DomainSeason] in the DB
    @JsonClass(generateAdapter = true)
    @Entity(tableName = "season")
    data class DomainSeason(
        @PrimaryKey
        var id: Long = 0L,
        var startDate: String? = "",
        var endDate: String? = ""
    ): DomainEntities()

    data class DomainScore(
        var fullTime: DomainSubScores = DomainSubScores(),
        var halfTime: DomainSubScores = DomainSubScores(),
        var duration: String = ""
    ): DomainEntities()

    data class DomainSubScores(
        var homeTeam: Int? = 0,
        var awayTeam: Int? = 0
    ): DomainEntities()

    data class DomainSubTeams(
        var id: Long = 0L,
        var name: String = ""
    ): DomainEntities()

    /**
     * Data classes for Competitions
     */
    data class DomainCompetitionResponse(
        var competitions: List<DomainCompetitions> = ArrayList()
    ): DomainEntities()

    @Entity(tableName = "competitions")
    data class DomainCompetitions(
        @PrimaryKey
        var id: Long = 0L,
        var name: String = "",
        var currentSeason: DomainSeason = DomainSeason()
    ): DomainEntities()

    /**
     * Data classes for Teams
     */
    data class DomainTeamResponse(
        var teams: List<DomainTeam> = ArrayList()
    ): DomainEntities()

    data class DomainTeam(
        var id: Long = 0L,
        var name: String = "",
        var shortName: String = "",
        var crestUrl: String = ""
    ): DomainEntities()

    /**
     * Data classes for Players
     */
    data class DomainPlayerResponse(
        var id: Long = 0L,
        var name: String = "",
        var shortName: String = "",
        var crestUrl: String = "",
        var squad: List<DomainPlayer> = ArrayList(),
    ): DomainEntities()

    data class DomainPlayer(
        var id: Long = 0L,
        var name: String = "",
        var position: String? = "",
        var role: String = "",
        var count: Int = 0
    ): DomainEntities()

    /**
     * Data classes for Standings
     */
    data class DomainStandingResponse(
        var standings: List<DomainStanding> = ArrayList()
    ): DomainEntities()

    data class DomainStanding(
        var table: List<DomainTable> = ArrayList()
    ): DomainEntities()

    data class DomainTable(
        var position: Int = 0,
        var team: DomainTeam = DomainTeam(),
        var playedGames: Int = 0,
        var points: Int = 0,
        var goalDifference: Int = 0
    ): DomainEntities()

}