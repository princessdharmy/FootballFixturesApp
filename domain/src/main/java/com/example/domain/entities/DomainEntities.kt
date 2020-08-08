package com.example.domain.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.ArrayList

sealed class DomainEntities {

    /**
     * Data classes for Matches
     */
    data class DomainMatchResponse(
        var matches: List<DomainMatch> = ArrayList(),
        var errorMessage: String = ""
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
        var competitions: List<DomainCompetitions> = ArrayList(),
        var errorMessage: String = ""
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
        var teams: List<DomainTeam> = ArrayList(),
        var errorMessage: String = ""
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
        var errorMessage: String = ""
    ): DomainEntities()

    //@Parcelize
    data class DomainPlayer(
        var id: Long = 0L,
        var name: String = "",
        var position: String = "",
        var role: String = "",
        var count: Int = 0
    ): DomainEntities()
        //: Parcelable

    /**
     * Data classes for Standings
     */
    data class DomainStandingResponse(
        var standings: List<DomainStanding> = ArrayList(),
        var errorMessage: String = ""
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