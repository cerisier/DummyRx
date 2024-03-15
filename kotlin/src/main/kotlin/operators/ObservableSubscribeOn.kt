package operators

import Observable
import Observer
import Scheduler

class ObservableSubscribeOn<T>(
    private val source: Observable<T>,
    private val scheduler: Scheduler
) : Observable<T>() {

    override fun subscribeActual(observer: Observer<T>) {
        scheduler.schedule {
            source.subscribe(ObserverSubscribeOn(observer))
        }
    }

    inner class ObserverSubscribeOn(private val downStream: Observer<T>): Observer<T> {
        override fun onNext(value: T) {
            downStream.onNext(value)
        }

        override fun onComplete() {
            downStream.onComplete()
        }
    }

}