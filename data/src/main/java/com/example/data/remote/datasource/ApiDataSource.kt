package com.example.data.remote.datasource

import com.example.data.models.DataCompetitionResponse
import com.example.data.models.DataMatchResponse
import io.reactivex.Single

interface ApiDataSource {

    fun getAllMatches(date: String): Single<DataMatchResponse>

    fun getAllCompetitions(): Single<DataCompetitionResponse>

}