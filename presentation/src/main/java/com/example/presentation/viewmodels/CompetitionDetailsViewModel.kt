package com.example.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import com.example.domain.usecases.competitiondetails.GetFixtureUseCase
import com.example.domain.usecases.competitiondetails.GetFixtureUseCase.Companion.make
import com.example.domain.usecases.competitiondetails.GetPlayersUseCase
import com.example.domain.usecases.competitiondetails.GetTableUseCase
import com.example.domain.usecases.competitiondetails.GetTeamUseCase
import com.example.presentation.mappers.map
import com.example.presentation.models.*
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.functions.Function
import javax.inject.Inject

class CompetitionDetailsViewModel @Inject constructor(
    private val getTableUseCase: GetTableUseCase,
    private val getFixtureUseCase: GetFixtureUseCase,
    private val getTeamUseCase: GetTeamUseCase,
    private val getPlayersUseCase: GetPlayersUseCase
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

    fun getTeams(id: Long): LiveData<Resource<TeamResponse>> {
        return getTeamUseCase
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

    fun getPlayers(id: Long): LiveData<Resource<PlayerResponse>> {
        return getPlayersUseCase
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
}
