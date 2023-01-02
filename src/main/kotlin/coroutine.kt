import kotlinx.coroutines.*
import kotlin.concurrent.thread

fun main() = runBlocking { //create a blocking coroutine that execute in current thread
    println("main program starts: ${Thread.currentThread().name}")

    val job:Job = launch(context = Dispatchers.Default) {
        delay(500)
        println("fake work starts: ${Thread.currentThread().name}")
        delay(1000)
        println("fake work finish: ${Thread.currentThread().name}")
    }

    //job.join()
    println("main program finish: ${Thread.currentThread().name}")

}

