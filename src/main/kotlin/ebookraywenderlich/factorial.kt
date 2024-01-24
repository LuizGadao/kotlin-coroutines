package ebookraywenderlich

import java.math.BigInteger
import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    val read = Scanner(System.`in`)

    println("Enter number:")
    val numb = read.nextInt()

    var factorialStr = ""
    var factorial: BigInteger = BigInteger.ONE

    val timeMeasureFactorial = measureTimeMillis { factorial = factorial(numb) }
    val timeMeasureFactorialRec = measureTimeMillis { factorial = recursiveFactorial(numb) }
    val timeMeasureToString = measureTimeMillis { factorialStr = factorial.toString() }

    println("Factorial of $numb is $factorial \n" +
            "in: $timeMeasureFactorial milliseconds\n" +
            "recursive factorial $timeMeasureFactorialRec milliseconds\n" +
            "toString measure time $timeMeasureToString milliseconds"
    )

    val myFactorial = 10
    val task = 3
    myTest(myFactorial, task)
}

fun myTest(factorial: Int, task: Int) {
    val quo = factorial / task
    val myRange = (1..factorial)
    val result = myRange.chunked(quo)
        .map { Pair(it.first(), it.last()) }
    //val result = myRange.windowed(quo, quo)
    println(result)
}

fun factorial(value: Int) : BigInteger {
    var fac = BigInteger.ONE

    for (i in 1..value) {
        fac = fac.multiply(i.toBigInteger())
    }

    return fac
}

fun recursiveFactorial(value: Int) : BigInteger {
    return if (value <= 1)
        BigInteger.ONE
    else
        value.toBigInteger() * recursiveFactorial(value - 1)
}