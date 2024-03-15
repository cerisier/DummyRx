package operators

import Observable
import Observer
import Scheduler

class ObservableObserveOn<T>(
    private val source: Observable<T>,
    private val scheduler: Scheduler
) : Observable<T>() {

    override fun subscribeActual(observer: Observer<T>) {
        source.subscribe(ObserverObserveOn(observer))
    }

    inner class ObserverObserveOn(private val downStream: Observer<T>): Observer<T> {
        override fun onNext(value: T) {
            scheduler.schedule {
                downStream.onNext(value)
            }
        }

        override fun onComplete() {
            scheduler.schedule {
                downStream.onComplete()
            }
        }
    }

}