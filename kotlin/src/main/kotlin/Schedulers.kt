import schedulers.ExecutorScheduler
import java.util.concurrent.ExecutorService

class Schedulers {
    companion object {
        fun from(executor: ExecutorService): Scheduler {
            return ExecutorScheduler(executor)
        }
    }
}