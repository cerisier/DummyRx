import operators.ObservableDoOnEach
import operators.ObservableFromValue
import operators.ObservableMap
import operators.ObservableObserveOn
import operators.ObservableSubscribeOn

open class Observable<T> {

    open fun subscribeActual(observer: Observer<T>) {}

    fun subscribe(observer: Observer<T>) {
        subscribeActual(observer)
    }

    fun subscribe(onNext: (T) -> Unit) {
        subscribe(object : Observer<T> {
            override fun onNext(value: T) {
                onNext(value)
            }
        })
    }

    fun doOnEach(onNext: (T) -> Unit = {}, onComplete: () -> Unit = {}, onSubscribe: () -> Unit = {}): Observable<T> {
        return ObservableDoOnEach(this, onNext, onComplete, onSubscribe)
    }

    fun <U>map(mapper: (T) -> U): Observable<U> {
        return ObservableMap(this, mapper)
    }

    fun subscribeOn(scheduler: Scheduler): Observable<T> {
        return ObservableSubscribeOn(this, scheduler)
    }

    fun observeOn(scheduler: Scheduler): Observable<T> {
        return ObservableObserveOn(this, scheduler)
    }

    companion object {
        fun <T>just(value: T): Observable<T> {
            return ObservableFromValue(value)
        }
    }
}