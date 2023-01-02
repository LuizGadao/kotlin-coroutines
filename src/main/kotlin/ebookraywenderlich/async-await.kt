package ebookraywenderlich

import MyUser
import kotlinx.coroutines.*
import java.io.File
import kotlin.coroutines.CoroutineContext
import kotlin.system.measureTimeMillis

fun getUserFromNetwork(id: Int, scope:CoroutineScope) = scope.async {
    println("start get user from network")
    Thread.sleep(3000)
    println("finish get user from network")
    MyUser(id, "Luiz", "Anjos")
}

fun getUserFromFile(filePath: String, scope: CoroutineScope) = scope.async {
    println("start reading file: $filePath")
    delay(1000)
    val users: List<MyUser>

    val time = measureTimeMillis {
        users = File(filePath)
            .readLines()
            .asSequence()
            .map {
                val data = it.split(" ")
                if (data.size == 3) data else emptyList()
            }
            .filter { it.isNotEmpty() }
            .map {
                val id = it[0].toInt()
                val name = it[1]
                val last = it[2]
                MyUser(id, name, last)
            }
            .toList()
    }

    println("time to read file: $time")
    users
}

class CustomScope : CoroutineScope {

    val parentJob = Job()

    override val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    fun stop() {
        parentJob.cancel()
    }

}

fun main() {

    val customScope = CustomScope()

    customScope.launch {
        println("Find User")

        val userDeferred = getUserFromNetwork(99, this)
        val usersDeferred = getUserFromFile("users.txt", this)

        val userStoredInFile = checkUserExist(userDeferred.await(), usersDeferred.await())
        if (userStoredInFile) {
            println("the user in users")
        } else {
            println("not find user")
        }
    }

    customScope.stop()

    Thread.sleep(5000)

}

fun checkUserExist(user: MyUser, users: List<MyUser>) = user in users
