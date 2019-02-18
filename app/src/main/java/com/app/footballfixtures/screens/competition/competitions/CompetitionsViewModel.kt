package com.app.footballfixtures.screens.competition.competitions

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.app.footballfixtures.core.data.Repository
import com.app.footballfixtures.core.data.models.CompetitionResponse
import com.app.footballfixtures.core.data.models.Competitions
import javax.inject.Inject

class CompetitionsViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    var liveData = MutableLiveData<CompetitionResponse>()

    fun getCompetitions(): LiveData<CompetitionResponse> {
        liveData = repository.getCompetitions() as MutableLiveData<CompetitionResponse>
        return liveData
    }

}
