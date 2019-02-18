package com.app.footballfixtures.screens.competition.today

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel;
import com.app.footballfixtures.core.data.Repository
import com.app.footballfixtures.core.data.models.MatchResponse
import javax.inject.Inject

class TodayFixturesViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    var liveData = MutableLiveData<MatchResponse>()

    fun getMatches(date: String): LiveData<MatchResponse> {
        liveData = repository.getMatches(date) as MutableLiveData<MatchResponse>
        return liveData
    }

}
