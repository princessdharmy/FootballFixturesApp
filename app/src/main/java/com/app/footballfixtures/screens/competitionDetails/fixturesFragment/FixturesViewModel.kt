package com.app.footballfixtures.screens.competitionDetails.fixturesFragment

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel;
import com.app.footballfixtures.core.data.Repository
import com.app.footballfixtures.core.data.models.MatchResponse
import javax.inject.Inject

class FixturesViewModel @Inject constructor(
    private var repository: Repository
) : ViewModel() {

    var liveData = MutableLiveData<MatchResponse>()

    fun getSingleMatch(id: Long, date: String): LiveData<MatchResponse> {
        liveData = repository.getSingleMatch(id, date) as MutableLiveData<MatchResponse>
        return liveData
    }
}
