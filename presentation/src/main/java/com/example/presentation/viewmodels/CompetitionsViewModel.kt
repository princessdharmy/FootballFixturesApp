package com.example.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import com.example.domain.usecases.GetCompetitionsUseCase
import com.example.domain.usecases.GetTodayFixturesUseCase
import com.example.presentation.mappers.map
import com.example.presentation.models.CompetitionResponse
import com.example.presentation.models.MatchResponse
import com.example.presentation.models.Resource
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.Function
import javax.inject.Inject

class CompetitionsViewModel @Inject constructor(
    private val getTodayFixturesUseCase: GetTodayFixturesUseCase,
    private val getCompetitionsUseCase: GetCompetitionsUseCase
) : ViewModel() {

    fun getAllMatches(date: String): LiveData<Resource<MatchResponse>> {
        return getTodayFixturesUseCase
            .run(date)
            .map { Resource.success(it.map()) }
            .toObservable()
            .startWith(Resource.loading())
            .onErrorResumeNext(
                Function {
                    Observable.just(Resource.error(msg = "An Error Occurred", data = null))
                }
            )
            .toFlowable(BackpressureStrategy.LATEST)
            .toLiveData()
    }

    fun getAllCompetitions(): LiveData<Resource<CompetitionResponse>> {
        return getCompetitionsUseCase
            .run()
            .map { Resource.success(it.map()) }
            .toObservable()
            .startWith(Resource.loading())
            .onErrorResumeNext(
                Function {
                    Observable.just(Resource.error(msg = "An Error Occurred", data = null))
                }
            )
            .toFlowable(BackpressureStrategy.LATEST)
            .toLiveData()
    }

}
