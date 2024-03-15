package operators

import Observable
import Observer

class ObservableMap<T, U>(
    private val source: Observable<T>,
    private val mapper: (T) -> U
) :  Observable<U>() {

    override fun subscribeActual(observer: Observer<U>) {
        source.subscribe(ObserverMap(observer))
    }

    inner class ObserverMap(private val observer: Observer<U>): Observer<T> {
        override fun onNext(value: T) {
            observer.onNext(mapper.invoke(value))
        }

        override fun onComplete() {
            observer.onComplete()
        }
    }
}