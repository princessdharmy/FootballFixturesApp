package com.example.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.common.utils.network.NetworkStatus
import com.example.domain.usecases.competitiondetails.GetFixtureUseCase
import com.example.domain.usecases.competitiondetails.GetFixtureUseCase.Companion.make
import com.example.domain.usecases.competitiondetails.GetPlayersUseCase
import com.example.domain.usecases.competitiondetails.GetTableUseCase
import com.example.domain.usecases.competitiondetails.GetTeamUseCase
import com.example.presentation.mappers.map
import javax.inject.Inject

class CompetitionDetailsViewModel @Inject constructor(
    private val getTableUseCase: GetTableUseCase,
    private val getFixtureUseCase: GetFixtureUseCase,
    private val getTeamUseCase: GetTeamUseCase,
    private val getPlayersUseCase: GetPlayersUseCase
) : ViewModel() {

    fun getStandings(id: Long) = liveData {
        emit(NetworkStatus.Loading())
        when (val result = getTableUseCase.invoke(id)) {
            is NetworkStatus.Success -> emit(NetworkStatus.Success(result.data?.map()))
            is NetworkStatus.Error -> emit(NetworkStatus.Error(result.errorMessage, null))
        }
    }

    fun getSingleMatch(id: Long, date: String) = liveData {
        emit(NetworkStatus.Loading())
        when (val result = getFixtureUseCase.invoke(make(id, date))) {
            is NetworkStatus.Success -> emit(NetworkStatus.Success(result.data?.map()))
            is NetworkStatus.Error -> emit(NetworkStatus.Error(result.errorMessage, null))
        }
    }

    fun getTeams(id: Long) = liveData {
        emit(NetworkStatus.Loading())
        when (val result = getTeamUseCase.invoke(id)) {
            is NetworkStatus.Success -> emit(NetworkStatus.Success(result.data?.map()))
            is NetworkStatus.Error -> emit(NetworkStatus.Error(result.errorMessage, null))
        }
    }

    fun getPlayers(id: Long) = liveData {
        emit(NetworkStatus.Loading())
        when (val result = getPlayersUseCase.invoke(id)) {
            is NetworkStatus.Success -> emit(NetworkStatus.Success(result.data?.map()))
            is NetworkStatus.Error -> emit(NetworkStatus.Error(result.errorMessage, null))
        }
    }

}
