package com.example.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.domain.entities.DomainEntities.*

@Dao
interface CompetitionsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCompetitions(competitions: List<DomainCompetitions>)

    @Query("SELECT * FROM competitions")
    fun queryCompetitions(): List<DomainCompetitions>
}
