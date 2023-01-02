import kotlinx.coroutines.*

fun main() {

    val userId = 99

    val handler = CoroutineExceptionHandler { context, throwable ->
        println("error aqui: ${throwable.message}")
    }

    val parentJob = Job()

    val job = GlobalScope.launch(
        context = Dispatchers.IO + handler + parentJob
    ) {
        getUserByIdFromNetwork(userId) { user ->
            println(user)
            println(Thread.currentThread().name)
        }
    }

//    while (job.isActive) {
//        println("job active")
//    }

    parentJob.cancel()

    //to print stack trace before the program finish
    Thread.sleep(50)
}

private fun getUserByIdFromNetwork(id: Int, onUserReady: (MyUser) -> Unit) {
    Thread.sleep(3000)
    throw java.lang.IllegalArgumentException("invalid value")
    onUserReady(MyUser(id = id, name = "Luiz", lastName = "Anjos"))
}