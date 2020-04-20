package com.example.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.ArrayList

sealed class DomainEntities {

    /**
     * Data classes for Matches
     */
    data class MatchResponse(
        var matches: List<Match> = ArrayList(),
        var errorMessage: String = ""
    ): DomainEntities()

    data class Match(
        var id: Long = 0L,
        var competition: SubTeams? = null,
        var season: Season = Season(),
        var utcDate: String = "",
        var status: String = "",
        var matchday: Int = 0,
        var stage: String = "",
        var group: String = "",
        var lastUpdated: String = "",
        var score: Score = Score(),
        var homeTeam: SubTeams = SubTeams(),
        var awayTeam: SubTeams = SubTeams()
    ): DomainEntities()

    //@Entity(tableName = "season")
    data class Season(
        //@PrimaryKey
        var id: Long = 0L,
        var startDate: String? = "",
        var endDate: String? = ""
    ): DomainEntities()

    data class Score(
        var fullTime: SubScores = SubScores(),
        var halfTime: SubScores = SubScores(),
        var duration: String = ""
    ): DomainEntities()

    data class SubScores(
        var homeTeam: Int? = 0,
        var awayTeam: Int? = 0
    ): DomainEntities()

    data class SubTeams(
        var id: Long = 0L,
        var name: String = ""
    ): DomainEntities()

    /**
     * Data classes for Competitions
     */
    data class CompetitionResponse(
        var competitions: List<Competitions> = ArrayList(),
        var errorMessage: String = ""
    ): DomainEntities()

    //@Entity(tableName = "competitions")
    data class Competitions(
        //@PrimaryKey
        var id: Long = 0L,
        var name: String = "",
        var currentSeason: Season = Season()
    ): DomainEntities()

    /**
     * Data classes for Teams
     */
    data class TeamResponse(
        var teams: List<Team> = ArrayList(),
        var errorMessage: String = ""
    ): DomainEntities()

    data class Team(
        var id: Long = 0L,
        var name: String = "",
        var shortName: String = "",
        var crestUrl: String = ""
    ): DomainEntities()

    /**
     * Data classes for Players
     */
    data class PlayerResponse(
        var id: Long = 0L,
        var name: String = "",
        var shortName: String = "",
        var crestUrl: String = "",
        var squad: List<Player> = ArrayList(),
        var errorMessage: String = ""
    ): DomainEntities()

    //@Parcelize
    data class Player(
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
    data class StandingResponse(
        var standings: List<Standing> = ArrayList(),
        var errorMessage: String = ""
    ): DomainEntities()

    data class Standing(
        var table: List<Table> = ArrayList()
    ): DomainEntities()

    data class Table(
        var position: Int = 0,
        var team: Team = Team(),
        var playedGames: Int = 0,
        var points: Int = 0,
        var goalDifference: Int = 0
    ): DomainEntities()

}