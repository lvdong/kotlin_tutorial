import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlin.coroutines.experimental.coroutineContext

/**
 * Created by tony on 2018/9/9.
 */
fun main(args: Array<String>) {

    // 创建一个协程，并创建两个子协程
    val job = launch {

        // 其一使用不同的上下文
        val job1 = launch {

            println("job1: I have my own context and execute independently!")
            delay(1000)
            println("job1: I am not affected by cancellation of the request")
        }

        // 另一个继承父级上下文
        val job2 = launch(coroutineContext) {

            println("job2: I am a child of the job coroutine")
            delay(1000)
            println("job2: I will not execute this line if my parent job is cancelled")
        }

        job1.join()
        job2.join()
    }

    Thread.sleep(500)

    job.cancel() // 取消job

    Thread.sleep(2000)
}