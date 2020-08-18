package com.example.data.local.datasource

import com.example.data.local.room.CompetitionsDao
import com.example.domain.entities.DomainEntities.*

class LocalDataSourceImpl(private val competitionsDao: CompetitionsDao) : LocalDataSource {

    override suspend fun saveCompetitions(competitions: List<DomainCompetitions>) {
        competitionsDao.insertCompetitions(competitions)
    }

    override fun getAllCompetitionsFromDb(): List<DomainCompetitions> {
        return competitionsDao.queryCompetitions()
    }


}