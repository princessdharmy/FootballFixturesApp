package com.example.data.workmanager

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.example.domain.usecases.workmanager.RefreshCompetitionsUseCase

class CompetitionDataWorkerFactory(
    private val refreshCompetitionsUseCase: RefreshCompetitionsUseCase,
    private val workNetworkService: WorkNetworkService
) : WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        return when (workerClassName) {
            CompetitionDataWorker::class.java.name ->
                CompetitionDataWorker(appContext, workerParameters,
                    refreshCompetitionsUseCase, workNetworkService)
            else ->
                // Return null, so that the base class can delegate to the default WorkerFactory.
                null
        }
    }
}