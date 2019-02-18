package com.app.footballfixtures.core.dagger.components

import com.app.footballfixtures.FixturesApplication
import com.app.footballfixtures.core.dagger.builders.ActivityBuilder
import com.app.footballfixtures.core.dagger.modules.FixturesMainModule
import com.app.footballfixtures.core.dagger.modules.NetworkModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidSupportInjectionModule::class,
                    FixturesMainModule::class,
                    NetworkModule::class,
                    ActivityBuilder::class))
interface FixturesMainComponents: AndroidInjector<FixturesApplication> {

    @Component.Builder
    abstract class Builder: AndroidInjector.Builder<FixturesApplication>()
}