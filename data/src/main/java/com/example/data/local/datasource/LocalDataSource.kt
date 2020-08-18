package com.example.data.local.datasource

import com.example.domain.entities.DomainEntities.*

interface LocalDataSource {

    suspend fun saveCompetitions(competitions: List<DomainCompetitions>)

    fun getAllCompetitionsFromDb(): List<DomainCompetitions>

}