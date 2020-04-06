package com.example.domain.usecases.base

import io.reactivex.Observable
import io.reactivex.Scheduler


abstract class ObservableUseCase<T, Input>(private val backgroundScheduler: Scheduler,
                                           private val foregroundScheduler: Scheduler) {

    protected abstract fun build(input: Input?): Observable<T>

    fun run(input: Input?): Observable<T> {
        return build(input)
            .subscribeOn(backgroundScheduler)
            .observeOn(foregroundScheduler)
    }
}