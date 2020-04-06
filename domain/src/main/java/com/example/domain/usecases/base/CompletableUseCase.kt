package com.example.domain.usecases.base

import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableCompletableObserver


abstract class CompletableUseCase<T, Input>(private val backgroundScheduler: Scheduler,
                                            private val foregroundScheduler: Scheduler) {

    protected abstract fun build(input: Input?): Completable

    fun run(input: Input?): Completable {
        return build(input)
            .subscribeOn(backgroundScheduler)
            .observeOn(foregroundScheduler)
    }

}