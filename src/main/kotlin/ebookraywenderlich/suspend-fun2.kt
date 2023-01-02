package ebookraywenderlich

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun teste() = "Teste 1"

suspend fun testeSus(): String {
    delay(1000)
    return "Teste 2: ${Thread.currentThread().name}"
}

suspend fun teste3(): String {
    delay(500)
    return "Teste 3 ${Thread.currentThread().name}"
}



fun main() {
    with(GlobalScope) {

        launch {
            val teste = teste()
            println(teste)

            val teste2 = testeSus()
            println(teste2)

            println(teste3())
        }

    }

    Thread.sleep(2500)
}