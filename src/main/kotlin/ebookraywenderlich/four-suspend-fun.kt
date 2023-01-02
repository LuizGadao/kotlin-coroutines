package ebookraywenderlich

import kotlinx.coroutines.*
import kotlin.concurrent.thread

data class User(val userId: String, val name: String)

fun main() {
    println("start")
    /*getUserFromNetworkCallback("99") {
        println("user is: ${it.name}")
    }

    getUserFromNetworkCallbackWithThrowable("100") { user, throwable ->
        user?.run(::println)
        throwable?.run { println("Aconteceu algum error: ${this.message}") }
    }*/

    with(GlobalScope) {
        launch {
            println("start get user - thread: ${Thread.currentThread().name}")
            val user = getUserSuspend("999")
            println(user.name)
            println("end get user - thread: ${Thread.currentThread().name}")
        }

        launch {
            println("start get user with context - thread: ${Thread.currentThread().name}")
            val user = getUserSuspend2("999")
            println(user.name)
            println("end get user with context - thread: ${Thread.currentThread().name}")
        }
    }

    println("finish")

    Thread.sleep(1200)
}

fun getUser(id: String): User {
    Thread.sleep(1000)
    return User(id, "Luiz")
}

fun getUserFromNetworkCallback(id: String, onSuccess: (User) -> Unit) {
    thread {
        Thread.sleep(1000)
        onSuccess(User(id, "Carlos"))
    }
}

fun getUserFromNetworkCallbackWithThrowable(
    id: String,
    onResponse: (User?, Throwable?) -> Unit
) {
    thread {
        Thread.sleep(1100)

        try {
            val user = User(id, "Carlitos")
            throw java.lang.IllegalArgumentException("invalid id")
            //onResponse(user, null)
        } catch (error: Throwable) {
            onResponse(null, error)
        }
    }
}

suspend fun getUserSuspend(id: String): User {
    delay(1000)
    return User(id, "Carlitos Tevez")
}

suspend fun getUserSuspend2(id: String): User = withContext(context = Dispatchers.IO){
    delay(1000)
    User(id, "Carlitos Anjos")
}


