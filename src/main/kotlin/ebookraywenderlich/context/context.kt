package ebookraywenderlich.context

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

interface CoroutineContextProvider {
    fun context(): CoroutineContext
}

class MyCoroutineContextProvider(
    val context: CoroutineContext
) : CoroutineContextProvider {

    override fun context() = context

}

fun main() {

    val myDispatcher = Dispatchers.Default // Continuation interceptor
    val myJob = Job()
    val handle = CoroutineExceptionHandler { coroutineContext, throwable ->
        print("error coroutine -> ${throwable.message}")
    }

    val myCoroutineContext = MyCoroutineContextProvider(
        context = myDispatcher + myJob + handle + CoroutineName("MyCoroutineContext")
    )
    val context = myCoroutineContext.context

    GlobalScope.launch(context = context) {
        println("context: $context - \n" + Thread.currentThread().name)
    }

    Thread.sleep(50)
}