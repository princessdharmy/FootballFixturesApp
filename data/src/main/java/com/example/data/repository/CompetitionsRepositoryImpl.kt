package com.example.data.repository

import android.util.Log
import com.example.common.utils.Result
import com.example.data.local.datasource.LocalDataSource
import com.example.data.remote.datasource.RemoteDataSource
import com.example.domain.entities.DomainEntities.*
import com.example.domain.repository.CompetitionsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CompetitionsRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : CompetitionsRepository {

    override suspend fun getTodayMatches(date: String): Result<DomainMatchResponse> {
        return withContext(Dispatchers.IO) {
            val allMatches = remoteDataSource.getAllMatches(date)
            Log.v("RESULT", allMatches.toString())
            (allMatches as? Result.Success)?.let {
                if (it.data?.matches?.isNotEmpty()!!) {
                    return@withContext Result.Success(it.data)
                }
            }
            return@withContext Result.Error(Exception("Illegal state"))
        }
    }

    override suspend fun getAllCompetitions(): Result<DomainCompetitionResponse> {
        return withContext(Dispatchers.IO) {
            val allMatches = remoteDataSource.getAllCompetitions()
            (allMatches as? Result.Success)?.let {
                if (it.data?.competitions?.isNotEmpty()!!) {
                    return@withContext Result.Success(it.data)
                }
            }
            return@withContext Result.Error(Exception("Illegal state"))
        }
    }

    override suspend fun getStandings(id: Long): Result<DomainStandingResponse> {
        return withContext(Dispatchers.IO) {
            val allMatches = remoteDataSource.getStandings(id)
            (allMatches as? Result.Success)?.let {
                if (it.data?.standings?.isNotEmpty()!!) {
                    return@withContext Result.Success(it.data)
                }
            }
            return@withContext Result.Error(Exception("Illegal state"))
        }
    }

    override suspend fun getSingleMatch(id: Long, date: String): Result<DomainMatchResponse> {
        return withContext(Dispatchers.IO) {
            val allMatches = remoteDataSource.getSingleMatch(id, date)
            (allMatches as? Result.Success)?.let {
                if (it.data?.matches?.isNotEmpty()!!) {
                    return@withContext Result.Success(it.data)
                }
            }
            return@withContext Result.Error(Exception("Illegal state"))
        }
    }

    override suspend fun getTeam(id: Long): Result<DomainTeamResponse> {
        return withContext(Dispatchers.IO) {
            val allMatches = remoteDataSource.getTeam(id)
            (allMatches as? Result.Success)?.let {
                if (it.data?.teams?.isNotEmpty()!!) {
                    return@withContext Result.Success(it.data)
                }
            }
            return@withContext Result.Error(Exception("Illegal state"))
        }
    }

    override suspend fun getPlayers(id: Long): Result<DomainPlayerResponse> {
        return withContext(Dispatchers.IO) {
            val allMatches = remoteDataSource.getPlayers(id)
            (allMatches as? Result.Success)?.let {
                return@withContext Result.Success(it.data)
            }
            return@withContext Result.Error(Exception("Illegal state"))
        }
    }
}