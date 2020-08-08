/*
package com.example.data.local.mapper

import com.example.data.models.*
import com.example.domain.entities.DomainEntities

fun DataMatchResponse.map() = DomainEntities.MatchResponse(
    matches = matches.map { it.map() },
    errorMessage = errorMessage
)

fun DomainEntities.MatchResponse.map() = DataMatchResponse(
    matches = matches.map { it.map() },
    errorMessage = errorMessage
)

fun DataMatch.map() = DomainEntities.Match(
    id = id,
    competition = competition?.map(),
    season = season.map(),
    utcDate = utcDate,
    status = status,
    matchday = matchday,
    stage = stage,
    group = group,
    lastUpdated = lastUpdated,
    score = score.map(),
    homeTeam = homeTeam.map(),
    awayTeam = awayTeam.map()
)

fun DomainEntities.Match.map() = DataMatch(
    id = id,
    competition = competition?.map(),
    season = season.map(),
    utcDate = utcDate,
    status = status,
    matchday = matchday,
    stage = stage,
    group = group,
    lastUpdated = lastUpdated,
    score = score.map(),
    homeTeam = homeTeam.map(),
    awayTeam = awayTeam.map()
)

fun DataSeason.map() = DomainEntities.Season(id = id, startDate = startDate, endDate = endDate)

fun DomainEntities.Season.map() = DataSeason(id = id, startDate = startDate, endDate = endDate)

fun DataScore.map() =
    DomainEntities.Score(halfTime = halfTime.map(), fullTime = fullTime.map(), duration = duration)

fun DomainEntities.Score.map() =
    DataScore(halfTime = halfTime.map(), fullTime = fullTime.map(), duration = duration)

fun DataSubScores.map() = DomainEntities.SubScores(homeTeam = homeTeam, awayTeam = awayTeam)

fun DomainEntities.SubScores.map() = DataSubScores(homeTeam = homeTeam, awayTeam = awayTeam)

fun DataSubTeams.map() = DomainEntities.SubTeams(id = id, name = name)

fun DomainEntities.SubTeams.map() = DataSubTeams(id = id, name = name)

fun DataCompetitionResponse.map() = DomainEntities.CompetitionResponse(
    competitions = competitions.map { it.map() },
    errorMessage = errorMessage
)

fun DomainEntities.CompetitionResponse.map() = DataCompetitionResponse(
    competitions = competitions.map { it.map() },
    errorMessage = errorMessage
)

fun DataCompetitions.map() =
    DomainEntities.Competitions(id = id, name = name, currentSeason = currentSeason.map())

fun DomainEntities.Competitions.map() =
    DataCompetitions(id = id, name = name, currentSeason = currentSeason.map())

fun DataTeamResponse.map() = DomainEntities.TeamResponse(
    teams = teams.map { it.map() },
    errorMessage = errorMessage
)

fun DomainEntities.TeamResponse.map() = DataTeamResponse(
    teams = teams.map { it.map() },
    errorMessage = errorMessage
)

fun DataTeam.map() =
    DomainEntities.Team(id = id, name = name, shortName = shortName, crestUrl = crestUrl ?: "")

fun DomainEntities.Team.map() =
    DataTeam(id = id, name = name, shortName = shortName, crestUrl = crestUrl)

fun DataPlayerResponse.map() = DomainEntities.PlayerResponse(
    id = id,
    name = name,
    shortName = shortName,
    crestUrl = crestUrl ?: "",
    squad = squad!!.map { it.map() },
    errorMessage = errorMessage
)

fun DomainEntities.PlayerResponse.map() = DataPlayerResponse(
    id = id,
    name = name,
    shortName = shortName,
    crestUrl = crestUrl,
    squad = squad.map { it.map() },
    errorMessage = errorMessage
)

fun DataPlayer.map() =
    DomainEntities.Player(id = id, name = name, position = position ?: "", role = role, count = count)

fun DomainEntities.Player.map() =
    DataPlayer(id = id, name = name, position = position, role = role, count = count)

fun DataStandingResponse.map() = DomainEntities.StandingResponse(
    standings = standings.map { it.map() },
    errorMessage = errorMessage
)

fun DomainEntities.StandingResponse.map() = DataStandingResponse(
    standings = standings.map { it.map() },
    errorMessage = errorMessage
)

fun DataStanding.map() = DomainEntities.Standing(table = table.map { it.map() })

fun DomainEntities.Standing.map() = DataStanding(table = table.map { it.map() })

fun DataTable.map() = DomainEntities.Table(
    position = position,
    team = team.map(),
    playedGames = playedGames,
    points = points,
    goalDifference = goalDifference
)

fun DomainEntities.Table.map() = DataTable(
    position = position,
    team = team.map(),
    playedGames = playedGames,
    points = points,
    goalDifference = goalDifference
)*/
