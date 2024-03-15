package operators

import Observable
import Observer

class ObservableDoOnEach<T>(
    private val source: Observable<T>,
    private val onNext: (T) -> Unit,
    private val onComplete: () -> Unit,
    private val onSubscribe: () -> Unit,
) : Observable<T>() {

    override fun subscribeActual(observer: Observer<T>) {
        onSubscribe()
        source.subscribe(ObserverDoOnEach(observer))
    }

    inner class ObserverDoOnEach(private val downStream: Observer<T>): Observer<T> {
        override fun onNext(value: T) {
            this@ObservableDoOnEach.onNext(value)
            downStream.onNext(value)
        }

        override fun onComplete() {
            this@ObservableDoOnEach.onComplete()
            downStream.onComplete()
        }
    }
}