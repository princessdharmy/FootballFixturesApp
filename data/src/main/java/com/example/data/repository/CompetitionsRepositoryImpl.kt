package com.example.data.repository


import com.example.common.utils.network.NetworkStatus
import com.example.data.coroutines.DispatcherProvider
import com.example.data.local.datasource.LocalDataSource
import com.example.data.remote.datasource.RemoteDataSource
import com.example.data.utils.networkBoundResource
import com.example.domain.entities.DomainEntities.*
import com.example.domain.repository.CompetitionsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class CompetitionsRepositoryImpl(
    private val dispatcherProvider: DispatcherProvider,
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : CompetitionsRepository {

    override suspend fun getTodayMatches(date: String): NetworkStatus<DomainMatchResponse> {
        return withContext(dispatcherProvider.io()) { remoteDataSource.getAllMatches(date) }
    }

    @ExperimentalCoroutinesApi
    override suspend fun getAllCompetitions(): Flow<NetworkStatus<List<DomainCompetitions>>> {
        return networkBoundResource(
            query = { fetchLocalCompetitions() },
            fetch = { remoteDataSource.getAllCompetitions() },
            saveFetchResult = { response ->
                localDataSource.saveCompetitions(response.data?.competitions!!)
            },
            clearData = {}
        )
    }

    private fun fetchLocalCompetitions(): Flow<List<DomainCompetitions>> = flow {
        emit(localDataSource.getAllCompetitionsFromDb())
    }

    override suspend fun getStandings(id: Long): NetworkStatus<DomainStandingResponse> {
        return withContext(dispatcherProvider.io()) { remoteDataSource.getStandings(id) }
    }

    override suspend fun getSingleMatch(
        id: Long,
        date: String
    ): NetworkStatus<DomainMatchResponse> {
        return withContext(dispatcherProvider.io()) { remoteDataSource.getSingleMatch(id, date) }
    }

    override suspend fun getTeam(id: Long): NetworkStatus<DomainTeamResponse> {
        return withContext(dispatcherProvider.io()) {
            remoteDataSource.getTeam(id)
        }
    }

    override suspend fun getPlayers(id: Long): NetworkStatus<DomainPlayerResponse> {
        return withContext(dispatcherProvider.io()) {
            remoteDataSource.getPlayers(id)
        }
    }

}