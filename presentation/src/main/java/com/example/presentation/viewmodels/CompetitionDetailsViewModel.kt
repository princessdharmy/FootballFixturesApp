package com.example.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.common.utils.Result
import com.example.domain.usecases.competitiondetails.GetFixtureUseCase
import com.example.domain.usecases.competitiondetails.GetFixtureUseCase.Companion.make
import com.example.domain.usecases.competitiondetails.GetPlayersUseCase
import com.example.domain.usecases.competitiondetails.GetTableUseCase
import com.example.domain.usecases.competitiondetails.GetTeamUseCase
import com.example.presentation.mappers.map
import com.example.presentation.models.*
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class CompetitionDetailsViewModel @Inject constructor(
    private val getTableUseCase: GetTableUseCase,
    private val getFixtureUseCase: GetFixtureUseCase,
    private val getTeamUseCase: GetTeamUseCase,
    private val getPlayersUseCase: GetPlayersUseCase
) : ViewModel() {

    fun getStandings(id: Long): LiveData<Resource<StandingResponse>>
            = liveData(Dispatchers.IO) {
        emit(Resource.loading())

        when (val result = getTableUseCase.invoke(id)) {
            is Result.Success -> {
                emit(Resource.success(result.data?.map()))
            }
            is Result.Error -> {
                emit(Resource.error(msg = "An Error Occurred", data = null))
            }
        }
    }

    fun getSingleMatch(id: Long, date: String): LiveData<Resource<MatchResponse>>
            = liveData(Dispatchers.IO) {
        emit(Resource.loading())

        when (val result = getFixtureUseCase.invoke(make(id, date))) {
            is Result.Success -> {
                emit(Resource.success(result.data?.map()))
            }
            is Result.Error -> {
//                emit(Resource.error(msg = "An Error Occurred", data = null))
                emit(Resource.error(msg = "${result.exception}"))
            }
        }
    }

    fun getTeams(id: Long): LiveData<Resource<TeamResponse>>
            = liveData(Dispatchers.IO) {
        emit(Resource.loading())

        when (val result = getTeamUseCase.invoke(id)) {
            is Result.Success -> {
                emit(Resource.success(result.data?.map()))
            }
            is Result.Error -> {
                emit(Resource.error(msg = "An Error Occurred", data = null))
            }
        }
    }

    fun getPlayers(id: Long): LiveData<Resource<PlayerResponse>>
            = liveData(Dispatchers.IO) {
        emit(Resource.loading())

        when (val result = getPlayersUseCase.invoke(id)) {
            is Result.Success -> {
                emit(Resource.success(result.data?.map()))
            }
            is Result.Error -> {
                emit(Resource.error(msg = "An Error Occurred", data = null))
            }
        }
    }

}
