object Task9Kotlin {
    @JvmStatic
    fun main(args: Array<String>) {
        val matrix = arrayOf(intArrayOf(4, 5, 6, 8), intArrayOf(1, 2, 3, 1), intArrayOf(7, 8, 9, 4), intArrayOf(1, 8, 7, 5), intArrayOf(1, 8, 7, 5), intArrayOf(1, 8, 7, 5))
        for (aMatrix in matrix) {
            for (j in 0 until matrix[0].size)
                print(aMatrix[j].toString() + " ")
            println()
        }
    }
}
