package ebookraywenderlich.exception

import kotlinx.coroutines.*

fun main() = runBlocking {
    val handler = CoroutineExceptionHandler{_, ex ->
        println("caught ex: ${ex.javaClass.simpleName}")
    }

    val supervisor = SupervisorJob()
    with(CoroutineScope(coroutineContext + supervisor + handler)) {
        // 2
        val firstChild = launch {
            println("First child throwing an exception")
            throw ArithmeticException()
        }
        // 3
        val secondChild = launch {
            println("First child is cancelled: ${firstChild.isCancelled}")
            try {
                delay(500)
                while (isActive) {
                    println("second child is active")
                }
            } catch (e: CancellationException) {
                println("Second child cancelled because supervisor got cancelled.")
            }
        }
        // 4
        firstChild.join()
        println("Second child is active: ${secondChild.isActive}")
        supervisor.cancel()
        secondChild.join()
    }
}