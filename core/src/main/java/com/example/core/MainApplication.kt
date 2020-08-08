package com.example.core

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.example.core.di.components.CoreComponent
import com.example.core.di.DaggerCoreComponent


class MainApplication: Application() {

    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(this)
    }

    companion object {
        @JvmStatic
        fun coreComponent(context: Context) =
            (context.applicationContext as MainApplication).coreComponent
    }

}

fun Activity.coreComponent() = MainApplication.coreComponent(this)
fun Fragment.coreComponent() = MainApplication.coreComponent(requireContext())