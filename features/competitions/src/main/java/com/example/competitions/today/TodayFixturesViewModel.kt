/*
package com.example.competitions.today

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel;
import com.example.data.Repository
import com.example.data.models.MatchResponse
import javax.inject.Inject

class TodayFixturesViewModel @Inject constructor(
    private val repository: com.example.data.Repository
) : ViewModel() {

    var liveData = MutableLiveData<MatchResponse>()

    fun getMatches(date: String): LiveData<MatchResponse> {
        liveData = repository.getMatches(date) as MutableLiveData<MatchResponse>
        return liveData
    }

}
*/
