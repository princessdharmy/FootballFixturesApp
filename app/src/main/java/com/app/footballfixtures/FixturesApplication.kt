package com.app.footballfixtures

import com.app.footballfixtures.core.dagger.components.DaggerFixturesMainComponents
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class FixturesApplication: DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerFixturesMainComponents.builder().create(this)
    }
}