package ebookraywenderlich.cancellation

import kotlinx.coroutines.*

fun main() = runBlocking {

    val startTime = System.currentTimeMillis()

    val job = launch(Dispatchers.Default) {
        var nextTime = startTime
        var i = 1

        while (i < 100) {
            //if (System.currentTimeMillis() >= nextTime && isActive)
            if (System.currentTimeMillis() >= nextTime) {
                yield() // if i call yield or delay function o don't need check isActive
                println("doing some work: $i")
                i++
                nextTime += 500L
            }
        }
    }

    delay(1000)
    println("cancelling")
    job.cancel()
    println("now i can quit")

}