package ebookraywenderlich.exception

import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope

fun main() = runBlocking {

    /*val result = async {
        println("Throwing exception in async")
        throw IllegalStateException()
    }

    try {
        result.await()
    } catch (e: Exception) {
        println("Caught $e")
    }*/

    supervisorScope {
        val result = async {
            println("Throwing exception in async")
            throw IllegalStateException()
        }

        try {
            result.await()
        } catch (e: Exception) {
            println("Caught $e")
        }
    }

}