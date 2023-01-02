import kotlinx.coroutines.*

fun main() = runBlocking {
    println("start program: ${Thread.currentThread().name}")

    /*
    withTimeout(2000) {
        try {
            repeat(500) {
                print("$it, ")
                delay(100)
            }
        } catch (ex: TimeoutCancellationException) {
            println("\n ${ex.message}")
        } finally {
            println("finally")
        }
    }
     */

    print(
        withTimeoutOrNull(2000) {
        repeat(500) {
            print("$it, ")
            delay(100)
        }

        "timeout task is done"
    } ?: "\nresult is null")



    println("\nend program: ${Thread.currentThread().name}")

}