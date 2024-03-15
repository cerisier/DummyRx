import java.util.concurrent.Executors

fun main(args: Array<String>) {

    Observable
        .just("corentin")
        .doOnEach(onNext = {
            println("Got value on ${Thread.currentThread().name}")
        })
        .map { str -> str.uppercase() }
        .doOnEach(onSubscribe = {
            println("Then susbcribed on ${Thread.currentThread().name}")
        })
        .subscribeOn(Schedulers.from(Executors.newFixedThreadPool(12)))
        .observeOn(Schedulers.from(Executors.newSingleThreadExecutor()))
        .doOnEach(onNext = {
            println("Then Got value on ${Thread.currentThread().name}")
        })
        .doOnEach(onSubscribe = {
            println("First susbcribed on ${Thread.currentThread().name}")
        })
        .subscribe(object: Observer<String> {
            override fun onNext(value: String) {
                println(Thread.currentThread().name)
                println(value)
            }

            override fun onComplete() {
                println("completed")
            }
        })
}