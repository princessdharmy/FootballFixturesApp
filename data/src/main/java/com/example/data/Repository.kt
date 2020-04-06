/*
package com.example.data

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.app.footballfixtures.core.data.db.CompetitionsDao
import com.app.footballfixtures.core.data.models.*
import com.app.footballfixtures.core.data.network.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(
    private val apiService: ApiService,
    private val dao: CompetitionsDao
)  {

    val competitionMutableLiveData = MutableLiveData<CompetitionResponse>()
    val matchMutableLiveData = MutableLiveData<MatchResponse>()
    val singleMatchMutableLiveData = MutableLiveData<MatchResponse>()
    val teamMutableLiveData = MutableLiveData<TeamResponse>()
    val playerMutableLiveData = MutableLiveData<PlayerResponse>()
    val standingMutableLiveData = MutableLiveData<StandingResponse>()

    val disposable = CompositeDisposable()

    */
/**
     * Passing all data to individual ViewModels
     *//*

    fun getCompetitions(): LiveData<CompetitionResponse> {
        fetchCompetitionsFromDb()
        return competitionMutableLiveData
    }

    fun getMatches(date: String): LiveData<MatchResponse> {
        fetchMatch(date)
        return matchMutableLiveData
    }

    fun getSingleMatch(id: Long, date: String): LiveData<MatchResponse> {
        fetchMatchByCompetition(id, date)
        return singleMatchMutableLiveData
    }

    fun getTeam(id: Long): LiveData<TeamResponse> {
        fetchTeam(id)
        return teamMutableLiveData
    }

    fun getPlayers(id: Long): LiveData<PlayerResponse> {
        fetchTeamById(id)
        return playerMutableLiveData
    }

    fun getStandings(id: Long, standingType: String): LiveData<StandingResponse> {
        fetchStanding(id, standingType)
        return standingMutableLiveData
    }

    */
/**
     * Fetch all data from endpoints
     *//*

    fun fetchCompetitionsFromApi() {
        val responseObservable = apiService.getAllCompetitions("TIER_ONE")
        disposable.add(
            responseObservable.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribeWith(object : DisposableSingleObserver<Response<CompetitionResponse>>() {
                    override fun onSuccess(t: Response<CompetitionResponse>) {
                        dao.insertCompetitions(t.body()?.competitions ?: ArrayList())
                        fetchCompetitionsFromDb()
                    }

                    override fun onError(e: Throwable) {
                        competitionMutableLiveData.postValue(CompetitionResponse(errorMessage = "No internet"))
                    }
                })
        )
    }

    @SuppressLint("CheckResult")
    fun fetchCompetitionsFromDb(): List<Competitions> {
        val list = ArrayList<Competitions>()
        val responseObservable = dao.queryCompetitions()
        responseObservable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .switchIfEmpty { }
            .firstOrError()
            .subscribe(
                { response ->
                    list.addAll(response)
                    if (!response.isEmpty()) {
                        competitionMutableLiveData.postValue(CompetitionResponse(competitions = response))
                    } else {
                        fetchCompetitionsFromApi()
                    }
                },
                { error ->
                    competitionMutableLiveData.postValue(
                        CompetitionResponse(
                            errorMessage = error.message ?: ""
                        )
                    )
                }
            )
        return list
    }

    fun fetchMatch(date: String) {
        val responseObservable = apiService.getAllMatches(date, date)
        disposable.add(
            responseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Response<MatchResponse>>() {
                    override fun onSuccess(t: Response<MatchResponse>) {
                        if (t.isSuccessful) {
                            matchMutableLiveData.postValue(MatchResponse(t.body()?.matches ?: ArrayList()))
                        } else {
                            matchMutableLiveData.postValue(MatchResponse(errorMessage = "No Internet"))
                        }
                    }

                    override fun onError(e: Throwable) {
                        matchMutableLiveData.postValue(MatchResponse(errorMessage = e.message ?: ""))
                    }

                })
        )
    }

    fun fetchMatchByCompetition(id: Long, date: String) {
        val responseObservable = apiService.getMatchesByCompetition(id, date, date)
        disposable.add(
            responseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Response<MatchResponse>>() {
                    override fun onSuccess(t: Response<MatchResponse>) {
                        if (t.isSuccessful) {
                            singleMatchMutableLiveData.postValue(MatchResponse(t.body()?.matches ?: ArrayList()))
                        } else {
                            singleMatchMutableLiveData.postValue(MatchResponse(errorMessage = "No Internet"))
                        }
                    }

                    override fun onError(e: Throwable) {
                        singleMatchMutableLiveData.postValue(MatchResponse(errorMessage = e.message ?: ""))
                    }

                })
        )
    }

    fun fetchTeam(id: Long) {
        val responseObservable = apiService.getTeamsByCompetition(id)
        disposable.add(
            responseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Response<TeamResponse>>() {
                    override fun onSuccess(t: Response<TeamResponse>) {
                        if (t.isSuccessful) {
                            teamMutableLiveData.postValue(TeamResponse(t.body()?.teams ?: ArrayList()))
                        } else {
                            teamMutableLiveData.postValue(TeamResponse(errorMessage = "No Internet"))
                        }
                    }

                    override fun onError(e: Throwable) {
                        teamMutableLiveData.postValue(TeamResponse(errorMessage = e.message ?: ""))
                    }

                })
        )
    }

    fun fetchTeamById(id: Long) {
        val responseObservable = apiService.getTeamById(id)
        disposable.add(
            responseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Response<PlayerResponse>>() {
                    override fun onSuccess(t: Response<PlayerResponse>) {
                        if (t.isSuccessful) {
                            playerMutableLiveData.postValue(
                                PlayerResponse(
                                    squad = t.body()?.squad ?: ArrayList(),
                                    name = t.body()?.name ?: "",
                                    shortName = t.body()?.shortName ?: "",
                                    id = t.body()?.id ?: 0L,
                                    crestUrl = t.body()?.crestUrl ?: ""
                                )
                            )
                        } else {
                            playerMutableLiveData.postValue(PlayerResponse(errorMessage = "No Internet"))
                        }
                    }

                    override fun onError(e: Throwable) {
                        playerMutableLiveData.postValue(PlayerResponse(errorMessage = e.message ?: ""))
                    }

                })
        )
    }

    fun fetchStanding(id: Long, standingType: String) {
        val responseObservable = apiService.getTablesByCompetition(id, standingType)
        disposable.add(
            responseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Response<StandingResponse>>() {
                    override fun onSuccess(t: Response<StandingResponse>) {
                        if (t.isSuccessful) {
                            standingMutableLiveData.postValue(StandingResponse(t.body()?.standings ?: ArrayList()))
                        } else {
                            standingMutableLiveData.postValue(StandingResponse(errorMessage = "No Internet"))
                        }
                    }

                    override fun onError(e: Throwable) {
                        standingMutableLiveData.postValue(StandingResponse(errorMessage = e.message ?: ""))
                    }

                })
        )
    }
}*/
