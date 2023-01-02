import kotlinx.coroutines.*
import java.lang.IllegalArgumentException

fun main() {

    GlobalScope.launch {
        println("start")

        launch {
            println("get user 100")
            try {
                val user = getUser(100)
                println("loaded user 100 $user")
            } catch (e: Exception) {
                println("catch error get user 100 ${e.message}")
            }
        }

        val deffer = async {
            getUser(101)
        }

        val deffer2 = async {
            getUser(102)
        }

        try {
            println("get user 101")
            println("loaded user 101: ${deffer.await()}")
        } catch (e: Exception) {
            println("catch error get user 101: ${e.message}")
        }

        try {
            //delay(3000)
            println("get user 102")
            println("loaded user 102: ${deffer2.await()}")
        } catch (e: Exception) {
            println("catch error get user 102: ${e.message}")
        }
    }
    Thread.sleep(5000)
    println("finish...")
}

suspend fun getUser(id: Int): MyUser {
    delay(500)
    throw IllegalArgumentException("invalid value")
    return MyUser(id, "luiz", "anjos")
}

