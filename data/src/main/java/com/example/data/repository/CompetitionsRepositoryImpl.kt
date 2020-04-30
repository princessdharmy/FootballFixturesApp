package com.example.data.repository

import com.example.data.local.mapper.map
import com.example.data.remote.datasource.ApiDataSource
import com.example.domain.entities.DomainEntities
import com.example.domain.repository.CompetitionsRepository
import io.reactivex.Single

class CompetitionsRepositoryImpl (private val apiDataSource: ApiDataSource): CompetitionsRepository {

    override fun getTodayMatches(date: String): Single<DomainEntities.MatchResponse> {
        return apiDataSource.getAllMatches(date).map { it.map() }
    }

    override fun getAllCompetitions(): Single<DomainEntities.CompetitionResponse> {
        return apiDataSource.getAllCompetitions().map { it.map() }
    }

}