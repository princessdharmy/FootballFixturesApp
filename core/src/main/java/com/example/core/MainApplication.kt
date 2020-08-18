package com.example.core

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.work.Configuration
import com.example.core.di.components.CoreComponent
import com.example.core.di.components.DaggerCoreComponent
import javax.inject.Inject


class MainApplication: Application(), Configuration.Provider {

    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(this)
    }

    @Inject
    lateinit var workerConfiguration: Configuration

    // Setup custom configuration for WorkManager with a DelegatingWorkerFactory
    override fun getWorkManagerConfiguration(): Configuration {
        return workerConfiguration
    }

    companion object {
        @JvmStatic
        fun coreComponent(context: Context) =
            (context.applicationContext as MainApplication).coreComponent
    }

}

fun Activity.coreComponent() = MainApplication.coreComponent(this)
fun Fragment.coreComponent() = MainApplication.coreComponent(requireContext())