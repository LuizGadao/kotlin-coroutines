import kotlinx.coroutines.*
import java.lang.IllegalArgumentException

fun main() = runBlocking {

    //sync
    /*
    repeat(10){
        val item = getItem(it)
        println(item)
    }
     */

    val handle = CoroutineExceptionHandler{ _, e ->
        println("caught exception: ${e.message}")
    }

    val job = supervisorScope {
        val items = mutableListOf<Deferred<String>>()
        repeat(10) {
            val item = async { getItem(it) }
            items.add(item)
        }

        try {
            items.awaitAll().forEach { println(it) }
        } catch (e: Exception) {
            println("caught exception when call await: ${e.message}")
        }
    }

    println("finish")
}

suspend fun getItem(i: Int): String {
    delay(1000)
    if (i == 5) {
        throw IllegalArgumentException("$i is invalid value")
    }
    println("getting item $i")
    return "get item: $i"
}
