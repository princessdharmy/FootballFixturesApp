package com.example.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import com.example.domain.usecases.GetFixtureUseCase
import com.example.domain.usecases.GetFixtureUseCase.Companion.make
import com.example.domain.usecases.GetTableUseCase
import com.example.presentation.mappers.map
import com.example.presentation.models.MatchResponse
import com.example.presentation.models.Resource
import com.example.presentation.models.StandingResponse
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.functions.Function
import javax.inject.Inject

class CompetitionDetailsViewModel @Inject constructor(
    private val getTableUseCase: GetTableUseCase,
    private val getFixtureUseCase: GetFixtureUseCase
) : ViewModel() {

    fun getStandings(id: Long): LiveData<Resource<StandingResponse>> {
        return getTableUseCase
            .run(id)
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

    fun getSingleMatch(id: Long, date: String): LiveData<Resource<MatchResponse>> {
        return getFixtureUseCase
            .run(make(id, date))
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
