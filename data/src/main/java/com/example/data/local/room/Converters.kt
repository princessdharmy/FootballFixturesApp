package com.example.data.local.room

import androidx.room.TypeConverter
import com.example.domain.entities.DomainEntities.*
import com.squareup.moshi.Moshi

class Converters {

        @TypeConverter
        fun fromSeason(season: DomainSeason): String {
            return Moshi.Builder().build().adapter(DomainSeason::class.java).toJson(season)
        }

        @TypeConverter
        fun toSeason(season: String): DomainSeason {
            return Moshi.Builder().build().adapter(DomainSeason::class.java).fromJson(season)!!
        }
}