import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    println("start program: ${Thread.currentThread().name}")

     val time = measureTimeMillis {
        //val msg1: Deferred<String> = async(start = CoroutineStart.LAZY){ getMessageOne() }
        val msg1: Deferred<String> = async{ getMessageOne() }
        val msg2: Deferred<String> = async{ getMessageTwo() }
        println("${msg1.await()} ${msg2.await()}")
    }
    println("time to complete: $time")

    println("\nend program: ${Thread.currentThread().name}")
}

suspend fun getMessageOne(): String {
    delay(1000)
    return "Hello"
}

suspend fun getMessageTwo(): String {
    delay(2000)
    return "Luiz"
}
