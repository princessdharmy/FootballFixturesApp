package com.example.domain.usecases.base

import io.reactivex.Scheduler
import io.reactivex.Single

/**
 * This interface represents an execution unit for any use case that implements it.
 *
 * By convention each Use Case implementation will return the result using a [DisposableSingleObserver]
 * that will execute its job in a background thread and will post the result in the UI thread.
 *
 * This use case is to be used when a single value to be emitted via a [Single] is expected.
 */
abstract class SingleUseCase<T, in Input> constructor(
    private val backgroundScheduler: Scheduler,
    private val foregroundScheduler: Scheduler
) {

    protected abstract fun build(input: Input?): Single<T>

    /**
     * This builds a [Single] which will be used when executing the current [SingleUseCase] class
     * with the provided Schedulers
     */
    fun run(input: Input? = null): Single<T>{
        return build(input)
            .subscribeOn(backgroundScheduler)
            .observeOn(foregroundScheduler)
    }

}