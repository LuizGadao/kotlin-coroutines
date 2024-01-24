package ebookraywenderlich.cancellation

import kotlinx.coroutines.*

fun main() = runBlocking{
    //joinAllEx(this)
    //cancelAndJoind(this)
    //cancelChildren(this)
    //withTimeout()
    withTimeoutExceptionHandler()
    //withTimeoutOrNull()
}

suspend fun withTimeoutOrNull() {
    val result = withTimeoutOrNull(1300L) {
        repeat(1000) { i ->
            println("$i. Crunching numbers [Beep.Boop.Beep]...")
            delay(500L)
        }
        "Done" // will get canceled before it produces this result
    }
    // Result will be `null`
    println("Result is $result")
}

suspend fun withTimeoutExceptionHandler() {
    try {
        withTimeout()
    } catch (e: TimeoutCancellationException) {
        println("caught: ${e.javaClass.simpleName} - ${e.message}")
    }
}

suspend fun withTimeout() {
    withTimeout(1500) {
        repeat(10) {
            println("repeat: $it    ")
            delay(500)
        }
    }
}

suspend fun cancelChildren(scope: CoroutineScope) {
    val parentJob = scope.launch {
        val childOne = launch {
            repeat(1000) { i ->
                println("Child Coroutine 1: " +
                        "$i. Crunching numbers [Beep.Boop.Beep]…")
                delay(500L)
            }
        }

        // Handle the exception thrown from `launch`
        // coroutine builder
        childOne.invokeOnCompletion { exception ->
            println("Child One: ${exception?.message}")
        }

        val childTwo = launch {
            repeat(1000) { i ->
                println("Child Coroutine 2: " +
                        "$i. Crunching numbers [Beep.Boop.Beep]…")
                delay(500L)
            }
        }

        // Handle the exception thrown from `launch`
        // coroutine builder
        childTwo.invokeOnCompletion { exception ->
            println("Child Two: ${exception?.message}")
        }

    }
    delay(1200L)

    println("Calling cancelChildren() on the parentJob")
    parentJob.cancelChildren()

    println("parentJob isActive: ${parentJob.isActive}")
}

suspend fun cancelAndJoind(scope: CoroutineScope) {
    val job = scope.launch {
        repeat(1000) { i ->
            println("$i. Crunching numbers [Beep.Boop.Beep]…")
            delay(500L)
        }
    }
    delay(1300L) // delay a bit
    println("main: I am tired of waiting!")
    // cancels the job and waits for job’s completion
    job.cancelAndJoin()
    println("main: Now I can quit.")
}

suspend fun joinAllEx(scope: CoroutineScope) {
    val jobOne = scope.launch {
        println("Job 1: Crunching numbers [Beep.Boop.Beep]…")
        delay(2000L)
    }

    val jobTwo = scope.launch {
        println("Job 2: Crunching numbers [Beep.Boop.Beep]…")
        delay(500L)
    }

    // waits for both the jobs to complete
    joinAll(jobOne, jobTwo)
    println("main: Now I can quit.")
}