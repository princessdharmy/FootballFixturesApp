package com.app.footballfixtures.screens.competitionDetails.teamFragment

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel;
import com.app.footballfixtures.core.data.Repository
import com.app.footballfixtures.core.data.models.PlayerResponse
import com.app.footballfixtures.core.data.models.TeamResponse
import javax.inject.Inject

class TeamViewModel @Inject constructor(
    private var repository: Repository
) : ViewModel() {

    var liveData = MutableLiveData<TeamResponse>()
    var playerLiveData = MutableLiveData<PlayerResponse>()

    fun getTeams(id: Long): LiveData<TeamResponse> {
        liveData = repository.getTeam(id) as MutableLiveData<TeamResponse>
        return liveData
    }

    fun getPlayers(id: Long): LiveData<PlayerResponse> {
        playerLiveData = repository.getPlayers(id) as MutableLiveData<PlayerResponse>
        return playerLiveData
    }

}
