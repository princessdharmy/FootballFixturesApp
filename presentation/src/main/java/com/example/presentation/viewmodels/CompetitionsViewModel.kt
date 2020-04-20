package com.example.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.domain.usecases.GetCompetitionsUseCase
import com.example.domain.usecases.GetTodayFixturesUseCase
import javax.inject.Inject

class CompetitionsViewModel @Inject constructor(
    private val getTodayFixturesUseCase: GetTodayFixturesUseCase,
    private val getCompetitionsUseCase: GetCompetitionsUseCase
) : ViewModel() {

//    var liveData = MutableLiveData<CompetitionResponse>()
//
//    fun getCompetitions(): LiveData<CompetitionResponse> {
//        liveData = repository.getCompetitions() as MutableLiveData<CompetitionResponse>
//        return liveData
//    }


}
