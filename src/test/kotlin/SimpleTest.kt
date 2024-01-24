import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class SimpleTest {

    @Test
    fun `my factorial test 1`() {
        val fatorial = 10
        val task = 2

        val pair1 = Pair(1, 5)
        val pair2 = Pair(6, 10)

        val expectedValues = listOf(pair1, pair2)

        val resultValues = getRangeList(fatorial, task)

        for ((index, value) in expectedValues.withIndex()) {
            Assertions.assertEquals(value, resultValues[index])
        }

        //Assertions.assertEquals(pair1, Pair(1, 5))
        //Assertions.assertEquals(pair2, Pair(6, 10))
    }

    @Test
    fun `my factorial test 2`() {
        val fatorial = 10
        val task = 3

        val pair1 = Pair(1, 3)
        val pair2 = Pair(4, 6)
        val pair3 = Pair(7, 10)

        val expectedValues = listOf(pair1, pair2, pair3)
        val resultValues = getRangeList(fatorial, task)

        for ((index, value) in expectedValues.withIndex()) {
            Assertions.assertEquals(value, resultValues[index])
        }

        //Assertions.assertEquals(pair1, Pair(1, 5))
        //Assertions.assertEquals(pair2, Pair(6, 10))
    }

    @Test
    fun `my factorial test 3`() {
        val fatorial = 10
        val task = 4

        val pair1 = Pair(1, 2)
        val pair2 = Pair(3, 4)
        val pair3 = Pair(5, 6)
        val pair4 = Pair(7, 10)

        val expectedValues = listOf(pair1, pair2, pair3, pair4)
        val resultValues = getRangeList(fatorial, task)

        for ((index, value) in expectedValues.withIndex()) {
            Assertions.assertEquals(value, resultValues[index])
        }

        //Assertions.assertEquals(pair1, Pair(1, 5))
        //Assertions.assertEquals(pair2, Pair(6, 10))
    }

    fun getRangeList(fatorial: Int, task: Int) : List<Pair<Int, Int>>{
        val quo = (fatorial / task)
        var startIndex = 1

        val myList: ArrayList<Pair<Int, Int>> = ArrayList()

        repeat(task -1) {
            val end = startIndex + (quo -1)
            myList.add(Pair(startIndex, end))
            startIndex += quo
        }

        myList.add(Pair(startIndex, fatorial))

        return myList
    }

}
