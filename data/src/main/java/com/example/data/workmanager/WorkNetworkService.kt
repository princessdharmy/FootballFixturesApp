package com.example.data.workmanager

import android.util.Log
import androidx.work.*

class WorkNetworkService {

    fun scheduleFetchData() {

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val conferenceDataWorker = OneTimeWorkRequestBuilder<CompetitionDataWorker>()
            .setInitialDelay(MINIMUM_DELAY, java.util.concurrent.TimeUnit.SECONDS)
            .setConstraints(constraints)
            .build()

        val operation = WorkManager.getInstance()
            .enqueueUniqueWork(
                uniqueCompetitionDataWorker,
                ExistingWorkPolicy.KEEP,
                conferenceDataWorker)
            .result

        operation.addListener(
            { Log.d("TAG", "CompetitionDataWorker enqueued..") },
            { it.run() }
        )
    }

    companion object {
        // Some delay to avoid load spikes
        private const val MINIMUM_DELAY = 5L

        // WorkManager UniqueWork string
        private const val uniqueCompetitionDataWorker = "UNIQUE_COMPETITION_DATA_WORKER"
    }
}