package com.example.data.remote.api

import com.example.data.BuildConfig.API_KEY
import com.example.domain.entities.DomainEntities.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("matches")
    @Headers("X-Auth-Token: $API_KEY")
    suspend fun getAllMatches(@Query("dateFrom") dateFrom: String,
                              @Query("dateTo") dateTo: String)
            : Response<DomainMatchResponse>


    @GET("competitions/{id}/matches")
    @Headers("X-Auth-Token: $API_KEY")
    suspend fun getMatchesByCompetition(@Path("id") id: Long,
                                        @Query("dateFrom") dateFrom: String,
                                        @Query("dateTo") dateTo: String)
            : Response<DomainMatchResponse>


    @GET("competitions")
    @Headers("X-Auth-Token: $API_KEY")
    suspend fun getAllCompetitions(@Query("plan") plan: String)
            : Response<DomainCompetitionResponse>


    @GET("competitions/{id}/teams")
    @Headers("X-Auth-Token: $API_KEY")
    suspend fun getTeamsByCompetition(@Path("id") id: Long)
            : Response<DomainTeamResponse>


    @GET("teams/{id}")
    @Headers("X-Auth-Token: $API_KEY")
    suspend fun getTeamById(@Path("id") id: Long)
            : Response<DomainPlayerResponse>


    @GET("competitions/{id}/standings")
    @Headers("X-Auth-Token: $API_KEY")
    suspend fun getTablesByCompetition(@Path("id") id: Long,
                                       @Query("standingType") standingType: String)
            : Response<DomainStandingResponse>
}