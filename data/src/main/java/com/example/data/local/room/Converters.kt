package com.example.data.local.room

import androidx.room.TypeConverter
import com.example.data.models.DataSeason
import com.squareup.moshi.Moshi

class Converters {

        @TypeConverter
        fun fromSeason(season: DataSeason): String {
            return Moshi.Builder().build().adapter(DataSeason::class.java).toJson(season)
        }

        @TypeConverter
        fun toSeason(season: String): DataSeason {
            return Moshi.Builder().build().adapter(DataSeason::class.java).fromJson(season)!!
        }
}