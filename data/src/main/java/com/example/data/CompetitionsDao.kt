package com.example.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.models.DataCompetitions
import io.reactivex.Flowable
import io.reactivex.Single


@Dao
interface CompetitionsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCompetitions(competitions: List<DataCompetitions>)

    @Query("SELECT * FROM competitions")
    fun queryCompetitions(): List<DataCompetitions>
}
