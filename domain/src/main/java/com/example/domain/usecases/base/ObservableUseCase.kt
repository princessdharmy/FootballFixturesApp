package com.example.domain.usecases.base

import io.reactivex.Observable
import io.reactivex.Scheduler

/**
 * This interface represents an execution unit for any use case that implements it.
 *
 * By convention each Use Case implementation will return the result using a [DisposableObserver]
 * that will execute its job in a background thread and will post the result in the UI thread.
 *
 * This use case is to be used when multiple values to be emitted via an [Observable] is expected.
 */
abstract class ObservableUseCase<T, in Input> constructor(
    private val backgroundScheduler: Scheduler,
    private val foregroundScheduler: Scheduler
) {

    protected abstract fun build(input: Input?): Observable<T>

    /**
     * This builds an [Observable] which will be used when executing the current [ObservableUseCase]
     * class with the provided Schedulers
     */
    fun run(input: Input?): Observable<T> {
        return build(input)
            .subscribeOn(backgroundScheduler)
            .observeOn(foregroundScheduler)
    }
}