package schedulers

import Scheduler
import java.util.concurrent.ExecutorService

class ExecutorScheduler(private val executor: ExecutorService): Scheduler {
    override fun schedule(runnable: Runnable) {
       executor.submit(runnable)
    }
}