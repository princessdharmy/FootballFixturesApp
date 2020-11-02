package com.example.presentation.viewmodels

import androidx.lifecycle.*
import com.example.domain.usecases.competition.GetCompetitionsUseCase
import com.example.domain.usecases.competition.GetTodayFixturesUseCase
import com.example.presentation.mappers.map
import com.example.presentation.models.Competitions
import com.example.common.utils.network.NetworkStatus
import javax.inject.Inject

class CompetitionsViewModel @Inject constructor(
    private val getTodayFixturesUseCase: GetTodayFixturesUseCase,
    private val getCompetitionsUseCase: GetCompetitionsUseCase
) : ViewModel() {

    fun getAllMatches(date: String) = liveData {
        emit(NetworkStatus.Loading())
        when (val result = getTodayFixturesUseCase.invoke(date)) {
            is NetworkStatus.Success -> emit(NetworkStatus.Success(result.data?.map()))
            is NetworkStatus.Error -> emit(NetworkStatus.Error(result.errorMessage!!, null))
        }
    }

    fun getAllCompetitions(): LiveData<List<Competitions>> = liveData {
        emit(getCompetitionsUseCase.invoke().map { it.map() })
    }

}
