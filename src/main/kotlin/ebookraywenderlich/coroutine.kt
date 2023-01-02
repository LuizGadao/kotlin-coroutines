package ebookraywenderlich

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() {
    //exA()
    //exB()
    exC()
}

private fun exC(){
    var isDoorOpen = false

    println("Unlocking the door... please wait.\n")
    GlobalScope.launch {
        delay(3000)

        isDoorOpen = true
    }

    GlobalScope.launch {
        repeat(4) {
            println("Trying to open the door...\n")
            delay(800)

            if (isDoorOpen) {
                println("Opened the door!\n")
            } else {
                println("The door is still locked\n")
            }
        }
    }

    Thread.sleep(5000)
}

private fun exB() {
    with(GlobalScope) {
        val parentJob = launch {
            delay(200)
            println("I'm the parent")
            delay(200)
        }

        val childJob = launch(context = parentJob) {
            delay(200)
            println("I'm the child")
            delay(200)
        }

        if (parentJob.children.iterator().hasNext())
            println("parent JOB has children")
        else
            println("parent JOB has no children")

        Thread.sleep(1000)
    }
}

private fun exA() {
    val jobA = GlobalScope.launch(start = CoroutineStart.LAZY) {
        delay(300)
        println("Carlos: ${Thread.currentThread().name}")
    }

    val jobB = GlobalScope.launch {
        delay(300)
        println("Luiz")
        jobA.join()
        println("Anjos: ${Thread.currentThread().name}")
        delay(300)
    }

    println("here: ${Thread.currentThread().name}")
    Thread.sleep(1000)
}