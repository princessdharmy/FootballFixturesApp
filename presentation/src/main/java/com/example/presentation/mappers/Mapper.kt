package com.example.presentation.mappers

import com.example.domain.entities.DomainEntities.*
import com.example.presentation.models.*

fun MatchResponse.map() = DomainMatchResponse(
    matches = matches.map { it.map() },
    errorMessage = errorMessage
)

fun DomainMatchResponse.map() = MatchResponse(
    matches = matches.map { it.map() },
    errorMessage = errorMessage
)

fun Match.map() = DomainMatch(
    id = id,
    competition = competition?.map(),
    season = season.map(),
    utcDate = utcDate,
    status = status ?: "",
    matchday = matchday,
    stage = stage,
    group = group,
    lastUpdated = lastUpdated,
    score = score.map(),
    homeTeam = homeTeam.map(),
    awayTeam = awayTeam.map()
)

fun DomainMatch.map() = Match(
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

fun Season.map() = DomainSeason(id = id, startDate = startDate, endDate = endDate)

fun DomainSeason.map() = Season(id = id, startDate = startDate, endDate = endDate)

fun Score.map() =
    DomainScore(halfTime = halfTime.map(), fullTime = fullTime.map(), duration = duration)

fun DomainScore.map() =
    Score(halfTime = halfTime.map(), fullTime = fullTime.map(), duration = duration)

fun SubScores.map() = DomainSubScores(homeTeam = homeTeam, awayTeam = awayTeam)

fun DomainSubScores.map() = SubScores(homeTeam = homeTeam, awayTeam = awayTeam)

fun SubTeams.map() = DomainSubTeams(id = id, name = name)

fun DomainSubTeams.map() = SubTeams(id = id, name = name)

fun CompetitionResponse.map() = DomainCompetitionResponse(
    competitions = competitions.map { it.map() },
    errorMessage = errorMessage
)

fun DomainCompetitionResponse.map() = CompetitionResponse(
    competitions = competitions.map { it.map() },
    errorMessage = errorMessage
)

fun Competitions.map() =
    DomainCompetitions(id = id, name = name, currentSeason = currentSeason.map())

fun DomainCompetitions.map() =
    Competitions(id = id, name = name, currentSeason = currentSeason.map())

fun TeamResponse.map() = DomainTeamResponse(
    teams = teams.map { it.map() },
    errorMessage = errorMessage
)

fun DomainTeamResponse.map() = TeamResponse(
    teams = teams.map { it.map() },
    errorMessage = errorMessage
)

fun Team.map() =
    DomainTeam(id = id, name = name, shortName = shortName, crestUrl = crestUrl ?: "")

fun DomainTeam.map() =
    Team(id = id, name = name, shortName = shortName, crestUrl = crestUrl)

fun PlayerResponse.map() = DomainPlayerResponse(
    id = id,
    name = name,
    shortName = shortName,
    crestUrl = crestUrl ?: "",
    squad = squad.map { it.map() },
    errorMessage = errorMessage
)

fun DomainPlayerResponse.map() = PlayerResponse(
    id = id,
    name = name,
    shortName = shortName,
    crestUrl = crestUrl,
    squad = squad.map { it.map() },
    errorMessage = errorMessage
)

fun Player.map() =
    DomainPlayer(id = id, name = name, position = position ?: "", role = role, count = count)

fun DomainPlayer.map() =
    Player(id = id, name = name, position = position, role = role, count = count)

fun StandingResponse.map() = DomainStandingResponse(
    standings = standings.map { it.map() },
    errorMessage = errorMessage
)

fun DomainStandingResponse.map() = StandingResponse(
    standings = standings.map { it.map() },
    errorMessage = errorMessage
)

fun Standing.map() = DomainStanding(table = table.map { it.map() })

fun DomainStanding.map() = Standing(table = table.map { it.map() })

fun Table.map() = DomainTable(
    position = position,
    team = team.map(),
    playedGames = playedGames,
    points = points,
    goalDifference = goalDifference
)

fun DomainTable.map() = Table(
    position = position,
    team = team.map(),
    playedGames = playedGames,
    points = points,
    goalDifference = goalDifference
)