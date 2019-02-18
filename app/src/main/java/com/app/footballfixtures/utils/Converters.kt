package com.app.footballfixtures.utils

import android.arch.persistence.room.TypeConverter
import android.os.Build
import android.support.annotation.RequiresApi
import com.app.footballfixtures.core.data.models.Season
import com.squareup.moshi.Moshi
import java.time.Instant

class Converters {

        @TypeConverter
        fun fromSeason(season: Season): String {
            return Moshi.Builder().build().adapter(Season::class.java).toJson(season)
        }

        @TypeConverter
        fun toSeason(season: String): Season {
            return Moshi.Builder().build().adapter(Season::class.java).fromJson(season)!!
        }
}