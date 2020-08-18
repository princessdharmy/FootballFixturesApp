package com.example.common.scopes

import androidx.work.Worker
import dagger.MapKey
import kotlin.reflect.KClass


@MapKey
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class WorkerKey(val value: KClass<out Worker>)