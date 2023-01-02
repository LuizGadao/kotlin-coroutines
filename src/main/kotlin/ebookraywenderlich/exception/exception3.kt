package ebookraywenderlich.exception

import kotlinx.coroutines.*
import java.lang.IllegalArgumentException

fun main() = runBlocking {
    val handle = CoroutineExceptionHandler{_, e ->
        println("caught ex: $e")
    }

    val job = GlobalScope.launch(handle) {
        throw IllegalArgumentException("invalid value")
    }

    val deffer = GlobalScope.async(handle) {
        throw ArithmeticException()
    }

    joinAll(job, deffer)
}