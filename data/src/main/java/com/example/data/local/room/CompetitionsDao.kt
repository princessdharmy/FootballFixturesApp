package com.example.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.domain.entities.DomainEntities.*

@Dao
interface CompetitionsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCompetitions(competitions: List<DomainCompetitions>): LongArray

    @Query("SELECT * FROM competitions")
    suspend fun queryCompetitions(): List<DomainCompetitions>
}
