object Task3Kotlin {

    @JvmStatic
    fun main(arg: Array<String>) {
        for (i in 0..12)
            for (j in 0..12)
                println(i.toString() + " * " + j + " = " + i * j)
    }

}
