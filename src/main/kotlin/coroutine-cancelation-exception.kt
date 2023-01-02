import kotlinx.coroutines.*

fun main() = runBlocking {

    println("start program")

    val job = launch(Dispatchers.Default) {

        try {
            repeat(500) {
                print("$it, ")
                delay(10)
            }
        } catch (ex: CancellationException) {
            print("\ncancel coroutine: ${ex.message}")
        } finally {
            withContext(NonCancellable) { // by default in finally this coroutine is cancellable
                delay(2000)
                println("\nclose resource in finally")
            }
        }
    }

    delay(300)
    job.cancel(CancellationException("I need cancel this coroutine"))
    job.join()

    println("\nend program")

}