object Task5Kotlin {
    @JvmStatic
    fun main(arg: Array<String>) {
        var array = intArrayOf(1, 2, 3, 4, 4, 5, 9, 1, 2)
        println("Largest number in array is: " + largestNumber(array))
        array = oddPositions(array)
        for (anArray in array) println(anArray)
    }

    private fun largestNumber(array: IntArray): Int {
        val max = array.max()
                ?: array[0]
        return max
    }

    private fun reverseArray(array: IntArray): IntArray {
        val temp = IntArray(array.size)
        for (i in array.indices)
            temp[i] = array[array.size - 1 - i]
        return temp
    }

    private fun oddPositions(array: IntArray): IntArray {
        val temp = IntArray(array.size / 2)
        array.indices
                .filter { it % 2 == 1 }
                .forEachIndexed { counter, i -> temp[counter] = array[i] }
        return temp
    }

    private fun returnArray(number: Int): IntArray {
        return IntArray(number)
    }
}
