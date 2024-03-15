interface Observer<T> {
    fun onNext(value: T) {}
    fun onComplete() {}
}