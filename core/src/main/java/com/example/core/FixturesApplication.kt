package com.example.core

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.example.core.di.CoreComponent
import com.example.core.di.DaggerCoreComponent


class FixturesApplication: Application() {

    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(this)
    }

    companion object {
        @JvmStatic
        fun coreComponent(context: Context) =
            (context.applicationContext as FixturesApplication).coreComponent
    }

}

fun Activity.coreComponent() = FixturesApplication.coreComponent(this)
fun Fragment.coreComponent() = FixturesApplication.coreComponent(requireContext())