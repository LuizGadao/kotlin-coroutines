import kotlinx.coroutines.*

fun main() = runBlocking {
    println("main program starts: ${Thread.currentThread().name}")

    val job:Deferred<Int> = async() {
        delay(500)
        println("fake work starts: ${Thread.currentThread().name}")
        delay(1000)
        println("fake work finish: ${Thread.currentThread().name}")
        52
    }

    val number = job.await()
    //job.join()
    println("main program finish: ${Thread.currentThread().name}\nvalue: $number")

}

