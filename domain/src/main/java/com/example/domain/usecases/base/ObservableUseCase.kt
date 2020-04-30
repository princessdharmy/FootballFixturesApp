package com.example.domain.usecases.base

import io.reactivex.Observable
import io.reactivex.Scheduler


abstract class ObservableUseCase<T, in Input> constructor(
    private val backgroundScheduler: Scheduler,
    private val foregroundScheduler: Scheduler
) {

    protected abstract fun build(vararg input: Input?): Observable<T>

    fun run(vararg input: Input?): Observable<T> {
        return build(*input)
            .subscribeOn(backgroundScheduler)
            .observeOn(foregroundScheduler)
    }
}