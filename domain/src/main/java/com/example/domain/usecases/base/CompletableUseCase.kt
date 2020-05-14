package com.example.domain.usecases.base

import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableCompletableObserver

/**
 * This interface represents an execution unit for any use case that implements it.
 *
 * By convention each Use Case implementation will return the result using a [DisposableCompletableObserver]
 * that will execute its job in a background thread and will post the result in the UI thread.
 *
 * This use case is to be used when no value to be emitted via a [Completable] is expected but
 * an action to be completed.
 */
abstract class CompletableUseCase<T, in Input> constructor(
    private val backgroundScheduler: Scheduler,
    private val foregroundScheduler: Scheduler) {

    protected abstract fun build(input: Input?): Completable

    /**
     * This builds a [Completable] which will be used when executing the current [CompletableUseCase]
     * class with the provided Schedulers
     */
    fun run(input: Input?): Completable {
        return build(input)
            .subscribeOn(backgroundScheduler)
            .observeOn(foregroundScheduler)
    }

}