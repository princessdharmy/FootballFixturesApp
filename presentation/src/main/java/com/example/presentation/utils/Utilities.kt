package com.example.presentation.utils

import android.annotation.SuppressLint
import android.util.Log
import com.example.presentation.models.Score
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.floor

object Utilities {

    @JvmStatic
    fun convertDate(date: String): String {
        //Creating a SimpleDateFormat in api pattern
        var dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())

        //Set the timezone to the api timezone
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")
        val dateFormatted = dateFormat.parse(date)

        //Setting a new pattern to match the expected format
        dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

        //Setting the timezone to match the local timezone
        dateFormat.timeZone = TimeZone.getDefault()
        return dateFormat.format(dateFormatted)
    }

    @JvmStatic
    fun getCurrentDate(): String {
        val date = Calendar.getInstance().time
        return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(date)

    }

    @JvmStatic
    fun convertSeasonStartDate(date: String): String {
        if (date.isNotEmpty()) {
            var seasonFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val dateFormat = seasonFormat.parse(date)
            seasonFormat = SimpleDateFormat("yyyy", Locale.getDefault())
            return seasonFormat.format(dateFormat)
        }
        return ""
    }

    @JvmStatic
    fun convertSeasonEndDate(date: String): String {
        if (date.isNotEmpty()) {
            var seasonFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val dateFormat = seasonFormat.parse(date)
            seasonFormat = SimpleDateFormat("yy", Locale.getDefault())
            return seasonFormat.format(dateFormat)
        }
        return ""
    }

    @JvmStatic
    fun convertRoleToPosition(role: String): String {
        return role.replace("_", " ").toLowerCase().capitalize()
    }

    @JvmStatic
    fun showMatchTime(status: String?, startTime: String?, score: Score?): String {
        return when (status) {
            "SCHEDULED" -> ("00")
            "PAUSED" -> ("HT")
            "FINISHED" -> ("FT")
            "POSTPONED" -> ("PPND")
            else -> calculateMatchTime(
                startTime,
                score
            )
        }
    }

    @JvmStatic
    fun getCurrentTime(): String {
        val time = Calendar.getInstance().time
        return SimpleDateFormat("HH:mm", Locale.getDefault()).format(time)
    }

    @SuppressLint("SimpleDateFormat")
    @JvmStatic
    fun calculateMatchTime(startTime: String?, score: Score?): String {
        val simpleDateFormat = SimpleDateFormat("HH:mm")
        val time: Int
        if (startTime.isNullOrEmpty()) {
            time = floor((simpleDateFormat.parse(getCurrentTime()).time / 60000.0)).toInt()
            return "$time\'"
        } else {
            return try {
                val startedTime = convertDate(startTime.toString())
                val currentTime = getCurrentTime()
                val onGoingMatchTime =
                    simpleDateFormat.parse(currentTime).time - simpleDateFormat.parse(startedTime).time
                time = if (score?.halfTime?.homeTeam != null || score?.halfTime?.awayTeam != null) {
                    floor((onGoingMatchTime / 60000.0)).toInt() - 15
                } else {
                    floor((onGoingMatchTime / 60000.0)).toInt()
                }
                "$time\'"
            } catch (e: IllegalArgumentException) {
                ""
            }
        }

    }

    fun hasInternetConnection(): Single<Boolean> {
        return Single.fromCallable {
            try {
                val timeoutMs = 1500
                val socket = Socket()
                val socketAddress = InetSocketAddress("api.football-data.org", 443)

                socket.connect(socketAddress, timeoutMs)
                socket.close()
                true
            } catch (e: IOException) {
                false
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}