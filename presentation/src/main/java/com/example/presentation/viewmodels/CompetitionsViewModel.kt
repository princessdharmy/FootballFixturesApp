package com.example.presentation.viewmodels

import androidx.lifecycle.*
import com.example.common.utils.Result
import com.example.domain.usecases.competition.GetCompetitionsUseCase
import com.example.domain.usecases.competition.GetTodayFixturesUseCase
import com.example.presentation.mappers.map
import com.example.presentation.models.Competitions
import com.example.presentation.models.MatchResponse
import com.example.presentation.models.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CompetitionsViewModel @Inject constructor(
    private val getTodayFixturesUseCase: GetTodayFixturesUseCase,
    private val getCompetitionsUseCase: GetCompetitionsUseCase
) : ViewModel() {

    fun getAllMatches(date: String): LiveData<Resource<MatchResponse>>
            = liveData(Dispatchers.IO) {
        emit(Resource.loading())

        when (val result = getTodayFixturesUseCase.invoke(date)) {
            is Result.Success -> {
                emit(Resource.success(result.data?.map()))
            }
            is Result.Error -> {
                emit(Resource.error(msg = "An Error Occurred", data = null))
            }
        }
    }


    fun getAllCompetitions(): LiveData<List<Competitions>> =
        liveData(Dispatchers.IO) {
            emit(getCompetitionsUseCase.invoke().map { it.map() })
        }

}
