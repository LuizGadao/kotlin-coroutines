import kotlinx.coroutines.*

fun main() = runBlocking {
    println("start program: ${Thread.currentThread().name}")

    val handler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Error aqui: $throwable")
    }

    val job = launch(Dispatchers.Default + handler) {

        repeat(500) {
            if (!isActive) {
                return@repeat
            }
            print("$it, ")
            delay(100)
            //yield()
            if (it == 5)
                throw java.lang.IllegalArgumentException("error value")
        }
        println("\njob finish ${Thread.currentThread().name}")
    }

    delay(1000)
    job.cancelAndJoin()
    println("\nend program: ${Thread.currentThread().name}")

    delay(50)
}