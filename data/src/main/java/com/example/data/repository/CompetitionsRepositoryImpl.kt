package com.example.data.repository


import com.example.common.utils.network.NetworkResult
import com.example.data.coroutines.DispatcherProvider
import com.example.data.local.datasource.LocalDataSource
import com.example.data.remote.datasource.RemoteDataSource
import com.example.domain.entities.DomainEntities.*
import com.example.domain.repository.CompetitionsRepository
import kotlinx.coroutines.withContext
import java.lang.Exception

class CompetitionsRepositoryImpl(
    private val dispatcherProvider: DispatcherProvider,
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : CompetitionsRepository {
    override suspend fun getTodayMatches(date: String): NetworkResult<DomainMatchResponse> {
        return withContext(dispatcherProvider.io()) {remoteDataSource.getAllMatches(date) }
//            val response = remoteDataSource.getAllMatches(date)
//            (response as? NetworkResult.Success)?.let {
////                if (it.data != null) {
//                    return@withContext NetworkResult.Success(it.data)
////            }
//            }
//            return@withContext NetworkResult.Error(response)
//        }
    }

    override suspend fun getAllCompetitionsFromDb(): List<DomainCompetitions> =
        withContext(dispatcherProvider.io()){ localDataSource.getAllCompetitionsFromDb() }

    override suspend fun getAllCompetitions() {
        return withContext(dispatcherProvider.io()) {
            val allCompetitions = remoteDataSource.getAllCompetitions()
            (allCompetitions as? NetworkResult.Success)?.let {
                if (it.data != null) {
                    localDataSource.saveCompetitions(it.data?.competitions!!)
                }
            }
        }
    }

    override suspend fun getStandings(id: Long): NetworkResult<DomainStandingResponse> {
        return withContext(dispatcherProvider.io()) { remoteDataSource.getStandings(id)
//            (allStandings as? Result.Success)?.let {
//                if (it.data?.standings != null) {
//                    return@withContext Result.Success(it.data)
//                }
//            }
//            return@withContext Result.Error(Exception("Illegal state"))
        }
    }

    override suspend fun getSingleMatch(id: Long, date: String): NetworkResult<DomainMatchResponse> {
        return withContext(dispatcherProvider.io()) { remoteDataSource.getSingleMatch(id, date)
//            (singleMatch as? Result.Success)?.let {
//                if (it.data?.matches != null) {
//                    return@withContext Result.Success(it.data)
//                }
//            }
//            return@withContext Result.Error(Exception("Illegal state"))
        }
    }

    override suspend fun getTeam(id: Long): NetworkResult<DomainTeamResponse> {
        return withContext(dispatcherProvider.io()) { remoteDataSource.getTeam(id)
//            (team as? Result.Success)?.let {
//                if (it.data?.teams!= null) {
//                    return@withContext Result.Success(it.data)
//                }
//            }
//            return@withContext Result.Error(Exception("Illegal state"))
        }
    }

    override suspend fun getPlayers(id: Long): NetworkResult<DomainPlayerResponse> {
        return withContext(dispatcherProvider.io()) { remoteDataSource.getPlayers(id)
//            (players as? Result.Success)?.let {
//                return@withContext Result.Success(it.data)
//            }
//            return@withContext Result.Error(Exception("Illegal state"))
//        }
        }
    }

}