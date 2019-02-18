package com.app.footballfixtures.screens.competitionDetails.tableFragment

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.app.footballfixtures.core.data.Repository
import com.app.footballfixtures.core.data.models.StandingResponse
import javax.inject.Inject

class TableViewModel @Inject constructor(
    private var repository: Repository
) : ViewModel() {

    var liveData = MutableLiveData<StandingResponse>()

    fun getStandings(id: Long, standingType: String): LiveData<StandingResponse> {
        liveData = repository.getStandings(id, standingType) as MutableLiveData<StandingResponse>
        return liveData
    }
}
