package com.example.presentation.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.domain.usecases.competition.GetCompetitionsUseCase
import com.example.domain.usecases.competition.GetTodayFixturesUseCase
import com.example.presentation.mappers.map
import com.example.presentation.models.Competitions
import com.example.common.utils.network.NetworkStatus
import com.example.domain.entities.DomainEntities.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class CompetitionsViewModel @ViewModelInject constructor(
    private val getTodayFixturesUseCase: GetTodayFixturesUseCase,
    private val getCompetitionsUseCase: GetCompetitionsUseCase
) : ViewModel() {

    private val _competitionsLiveData = MutableLiveData<NetworkStatus<List<Competitions>>>()
    val competitionsLiveData = _competitionsLiveData


    fun getAllMatches(date: String) = liveData {
        emit(NetworkStatus.Loading())
        when (val result = getTodayFixturesUseCase.invoke(date)) {
            is NetworkStatus.Success -> emit(NetworkStatus.Success(result.data?.map()))
            is NetworkStatus.Error -> emit(NetworkStatus.Error(result.errorMessage!!, null))
        }
    }

    fun getAllCompetitions() {
        viewModelScope.launch(Dispatchers.IO) {
            getCompetitionsUseCase.invoke().collect {
                updateData(it)
            }

        }
    }

    private fun updateData(response: NetworkStatus<List<DomainCompetitions>>) {
        when (response) {
            is NetworkStatus.Loading -> {
                if (response.data.isNullOrEmpty().not()) {
                    _competitionsLiveData.postValue(NetworkStatus.Loading(response.data?.map { it.map() }))
                } else {
                    _competitionsLiveData.postValue(NetworkStatus.Loading())
                }
            }
            is NetworkStatus.Success -> {
                if (response.data.isNullOrEmpty().not()) {
                    _competitionsLiveData.postValue(NetworkStatus.Success(response.data?.map { it.map() }))
                }
            }
            is NetworkStatus.Error -> {
                if (response.data.isNullOrEmpty().not()) {
                    _competitionsLiveData.postValue(
                        NetworkStatus.Error(
                            null,
                            response.data?.map { it.map() })
                    )
                } else {
                    _competitionsLiveData.postValue(
                        NetworkStatus.Error(
                            response.errorMessage,
                            null
                        )
                    )
                }
            }
        }
    }

}
