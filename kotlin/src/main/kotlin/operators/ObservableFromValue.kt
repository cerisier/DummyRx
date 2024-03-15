package operators

import Observable
import Observer

class ObservableFromValue<T>(
   private val value: T
): Observable<T>() {

    override fun subscribeActual(observer: Observer<T>) {
        observer.onNext(value)
        observer.onComplete()
    }
}