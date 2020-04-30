package com.example.data.remote.datasource

import com.example.data.local.mapper.map
import com.example.data.models.DataCompetitionResponse
import com.example.data.models.DataMatchResponse
import com.example.data.remote.api.ApiService
import io.reactivex.Single

class ApiDataSourceImpl (private val apiService: ApiService): ApiDataSource {

    override fun getAllMatches(date: String): Single<DataMatchResponse> {
        return apiService.getAllMatches(date, date)
    }

    override fun getAllCompetitions(): Single<DataCompetitionResponse> {
        return apiService.getAllCompetitions("TIER_ONE")
    }

}