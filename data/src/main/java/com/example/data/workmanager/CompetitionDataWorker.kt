package com.example.data.workmanager

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.domain.usecases.workmanager.RefreshCompetitionsUseCase
import java.lang.Exception

class CompetitionDataWorker (
    context: Context,
    params: WorkerParameters,
    private val refreshCompetitionsUseCase: RefreshCompetitionsUseCase
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        return try {
            Log.e("Tag", "Successful")
            Result.success()
        } catch (e: Exception) {
            Log.e("Tag", "Failed")
            Result.retry()
        }
    }
}