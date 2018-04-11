object Task4Kotlin {
    @JvmStatic
    fun main(arg: Array<String>) {
        var counter = 0
        var year = 2018
        do {
            if (year % 4 == 0) {
                println(year)
                counter++
            }
            year++
        } while (counter < 20)
    }
}
