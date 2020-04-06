package com.example.domain.usecases.base

import io.reactivex.Scheduler
import io.reactivex.Single


abstract class SingleUseCase<T, Input>(private val backgroundScheduler: Scheduler,
                                       private val foregroundScheduler: Scheduler) {

    protected abstract fun build(input: Input?): Single<T>

    fun run(input: Input?): Single<T>{
        return build(input)
            .subscribeOn(backgroundScheduler)
            .observeOn(foregroundScheduler)
    }

}