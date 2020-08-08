/*
package com.example.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

*/
/**
 * Data classes for Matches
 *//*

data class DataMatchResponse(
    var matches: List<DataMatch> = ArrayList(),
    var errorMessage: String = ""
)

data class DataMatch(
    var id: Long = 0L,
    var competition: DataSubTeams? = null,
    var season: DataSeason = DataSeason(),
    var utcDate: String = "",
    var status: String = "",
    var matchday: Int = 0,
    var stage: String = "",
    var group: String = "",
    var lastUpdated: String = "",
    var score: DataScore = DataScore(),
    var homeTeam: DataSubTeams = DataSubTeams(),
    var awayTeam: DataSubTeams = DataSubTeams()
)

@Entity(tableName = "season")
data class DataSeason(
    @PrimaryKey
    var id: Long = 0L,
    var startDate: String? = "",
    var endDate: String? = ""
)

data class DataScore(
    var fullTime: DataSubScores = DataSubScores(),
    var halfTime: DataSubScores = DataSubScores(),
    var duration: String = ""
)

data class DataSubScores(
    var homeTeam: Int? = 0,
    var awayTeam: Int? = 0
)

data class DataSubTeams(
    var id: Long = 0L,
    var name: String = ""
)

*/
/**
 * Data classes for Competitions
 *//*

data class DataCompetitionResponse(
    var competitions: List<DataCompetitions> = ArrayList(),
    var errorMessage: String = ""
)

@Entity(tableName = "competitions")
data class DataCompetitions(
    @PrimaryKey
    var id: Long = 0L,
    var name: String = "",
    var currentSeason: DataSeason = DataSeason()
)

*/
/**
 * Data classes for Teams
 *//*

data class DataTeamResponse(
    var teams: List<DataTeam> = ArrayList(),
    var errorMessage: String = ""
)

data class DataTeam(
    var id: Long = 0L,
    var name: String = "",
    var shortName: String = "",
    var crestUrl: String? = ""
)

*/
/**
 * Data classes for Players
 *//*

data class DataPlayerResponse(
    var id: Long = 0L,
    var name: String = "",
    var shortName: String = "",
    var crestUrl: String? = "",
    var squad: List<DataPlayer>? = ArrayList(),
    var errorMessage: String = ""
)

@Parcelize
data class DataPlayer(
    var id: Long = 0L,
    var name: String = "",
    var position: String? = "",
    var role: String = "",
    var count: Int = 0
): Parcelable

*/
/**
 * Data classes for Standings
 *//*

data class DataStandingResponse(
    var standings: List<DataStanding> = ArrayList(),
    var errorMessage: String = ""
)

data class DataStanding(
    var table: List<DataTable> = ArrayList()
)

data class DataTable(
    var position: Int = 0,
    var team: DataTeam = DataTeam(),
    var playedGames: Int = 0,
    var points: Int = 0,
    var goalDifference: Int = 0
)


*/
