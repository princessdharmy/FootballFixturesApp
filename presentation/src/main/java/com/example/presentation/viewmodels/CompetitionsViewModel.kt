package com.example.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.example.common.utils.network.NetworkResult
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
            is NetworkResult.Success -> emit(NetworkStatus.Success(result.data?.map()))
            is NetworkResult.Error -> emit(NetworkStatus.Error(result.errorMessage!!, null))
        }
    }


    fun getAllCompetitions(): LiveData<List<Competitions>> = liveData {
        emit(getCompetitionsUseCase.invoke().map { it.map() })
    }

}
