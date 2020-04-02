package com.app.footballfixtures.core.data.network

import com.app.footballfixtures.BuildConfig.API_KEY
import com.app.footballfixtures.core.data.models.*
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("matches")
    @Headers("X-Auth-Token: $API_KEY")
    fun getAllMatches(@Query("dateFrom") dateFrom: String,
                           @Query("dateTo") dateTo: String)
            : Single<Response<MatchResponse>>


    @GET("competitions/{id}/matches")
    @Headers("X-Auth-Token: $API_KEY")
    fun getMatchesByCompetition(@Path("id") id: Long,
                                @Query("dateFrom") dateFrom: String,
                                @Query("dateTo") dateTo: String)
            : Single<Response<MatchResponse>>


    @GET("competitions")
    @Headers("X-Auth-Token: $API_KEY")
    fun getAllCompetitions(@Query("plan") plan: String)
            : Single<Response<CompetitionResponse>>


    @GET("competitions/{id}/teams")
    @Headers("X-Auth-Token: $API_KEY")
    fun getTeamsByCompetition(@Path("id") id: Long)
            : Single<Response<TeamResponse>>


    @GET("teams/{id}")
    @Headers("X-Auth-Token: $API_KEY")
    fun getTeamById(@Path("id") id: Long)
            : Single<Response<PlayerResponse>>


    @GET("competitions/{id}/standings")
    @Headers("X-Auth-Token: $API_KEY")
    fun getTablesByCompetition(@Path("id") id: Long,
                               @Query("standingType") standingType: String)
            : Single<Response<StandingResponse>>
}