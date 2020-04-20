package com.example.presentation.mappers

import com.example.domain.entities.DomainEntities
import com.example.presentation.models.*

fun MatchResponse.map() = DomainEntities.MatchResponse(
    matches = matches.map { it.map() },
    errorMessage = errorMessage
)

fun DomainEntities.MatchResponse.map() = MatchResponse(
    matches = matches.map { it.map() },
    errorMessage = errorMessage
)

fun Match.map() = DomainEntities.Match(
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

fun DomainEntities.Match.map() = Match(
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

fun Season.map() = DomainEntities.Season(id = id, startDate = startDate, endDate = endDate)

fun DomainEntities.Season.map() = Season(id = id, startDate = startDate, endDate = endDate)

fun Score.map() =
    DomainEntities.Score(halfTime = halfTime.map(), fullTime = fullTime.map(), duration = duration)

fun DomainEntities.Score.map() =
    Score(halfTime = halfTime.map(), fullTime = fullTime.map(), duration = duration)

fun SubScores.map() = DomainEntities.SubScores(homeTeam = homeTeam, awayTeam = awayTeam)

fun DomainEntities.SubScores.map() = SubScores(homeTeam = homeTeam, awayTeam = awayTeam)

fun SubTeams.map() = DomainEntities.SubTeams(id = id, name = name)

fun DomainEntities.SubTeams.map() = SubTeams(id = id, name = name)

fun CompetitionResponse.map() = DomainEntities.CompetitionResponse(
    competitions = competitions.map { it.map() },
    errorMessage = errorMessage
)

fun DomainEntities.CompetitionResponse.map() = CompetitionResponse(
    competitions = competitions.map { it.map() },
    errorMessage = errorMessage
)

fun Competitions.map() =
    DomainEntities.Competitions(id = id, name = name, currentSeason = currentSeason.map())

fun DomainEntities.Competitions.map() =
    Competitions(id = id, name = name, currentSeason = currentSeason.map())

fun TeamResponse.map() = DomainEntities.TeamResponse(
    teams = teams.map { it.map() },
    errorMessage = errorMessage
)

fun DomainEntities.TeamResponse.map() = TeamResponse(
    teams = teams.map { it.map() },
    errorMessage = errorMessage
)

fun Team.map() =
    DomainEntities.Team(id = id, name = name, shortName = shortName, crestUrl = crestUrl)

fun DomainEntities.Team.map() =
    Team(id = id, name = name, shortName = shortName, crestUrl = crestUrl)

fun PlayerResponse.map() = DomainEntities.PlayerResponse(
    id = id,
    name = name,
    shortName = shortName,
    crestUrl = crestUrl,
    squad = squad.map { it.map() },
    errorMessage = errorMessage
)

fun DomainEntities.PlayerResponse.map() = PlayerResponse(
    id = id,
    name = name,
    shortName = shortName,
    crestUrl = crestUrl,
    squad = squad.map { it.map() },
    errorMessage = errorMessage
)

fun Player.map() =
    DomainEntities.Player(id = id, name = name, position = position, role = role, count = count)

fun DomainEntities.Player.map() =
    Player(id = id, name = name, position = position, role = role, count = count)

fun StandingResponse.map() = DomainEntities.StandingResponse(
    standings = standings.map { it.map() },
    errorMessage = errorMessage
)

fun DomainEntities.StandingResponse.map() = StandingResponse(
    standings = standings.map { it.map() },
    errorMessage = errorMessage
)

fun Standing.map() = DomainEntities.Standing(table = table.map { it.map() })

fun DomainEntities.Standing.map() = Standing(table = table.map { it.map() })

fun Table.map() = DomainEntities.Table(
    position = position,
    team = team.map(),
    playedGames = playedGames,
    points = points,
    goalDifference = goalDifference
)

fun DomainEntities.Table.map() = Table(
    position = position,
    team = team.map(),
    playedGames = playedGames,
    points = points,
    goalDifference = goalDifference
)