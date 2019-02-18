package com.app.footballfixtures.core.data.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.app.footballfixtures.core.data.models.Competitions
import io.reactivex.Flowable
import io.reactivex.Single


@Dao
interface CompetitionsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCompetitions(competitions: List<Competitions>)

    @Query("SELECT * FROM competitions")
    fun queryCompetitions(): Flowable<List<Competitions>>
}
